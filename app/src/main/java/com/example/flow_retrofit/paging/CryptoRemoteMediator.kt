package com.example.flow_retrofit.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.flow_retrofit.data.database.CryptoDatabase
import com.example.flow_retrofit.data.model.CryptoRemoteKey
import com.example.flow_retrofit.data.model.ResponseCoinsList
import com.example.flow_retrofit.data.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@ExperimentalPagingApi
class CryptoRemoteMediator @Inject
constructor(
    private val apiService: ApiService,
    private val cryptoDatabase: CryptoDatabase,
) : RemoteMediator<Int, ResponseCoinsList.ResponseCoinsListItem>() {


    private val cryptoDao = cryptoDatabase.getCryptoDao()
    private val remoteKeyDao = cryptoDatabase.getRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResponseCoinsList.ResponseCoinsListItem>,
    ): MediatorResult {

        // step 1 fetch quotes from api
        // step 2 save these quotes + remoteKeys data into database
        // step 3 logic for state - REFRESH , PREPEND , APPEND

        return try {

            val currentPage = when (loadType) {
                // Refresh -> when first load or data invalidate
                LoadType.REFRESH -> {
                    val remoteKeys = CoroutineScope(Dispatchers.IO).async {
                        getRemoteKeyClosestToCurrentPosition(state)
                    }.await()
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                //  Prepend -> when scroll up
                LoadType.PREPEND -> {
                    val remoteKeys = CoroutineScope(Dispatchers.IO).async {
                        getRemoteKeyForFirstItem(state)
                    }.await()
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                // Append -> When Scroll Down
                LoadType.APPEND -> {
                    val remoteKeys = CoroutineScope(Dispatchers.IO).async {
                        getRemoteKeyForLastItem(state)
                    }.await()
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }

            }

            // hit api
            val response = apiService.getCoinsList(
                vsCurrency = "inr",
                perPage = 10,
                page = currentPage
            ).body()?.map {
                it
            } ?: emptyList()

            // is last page == true
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            cryptoDatabase.withTransaction {

                // if refresh then first clear data from database to load new data
                if (loadType == LoadType.REFRESH) {
                    cryptoDao.deleteCryptoCoins()
                    remoteKeyDao.deleteRemoteKeys()
                }

                // store response in room database
                cryptoDao.addCryptoCoins(response)

                val keys = response.map { coinsItem ->
                    CryptoRemoteKey(
                        coinId = coinsItem.id,
                        nextPage = nextPage,
                        prevPage = prevPage
                    )
                }

                remoteKeyDao.insertRemoteKey(remoteKeys = keys)
            }

            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ResponseCoinsList.ResponseCoinsListItem>,
    ): CryptoRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getRemoteKeys(id = id)
            }
        }
    }

    private fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ResponseCoinsList.ResponseCoinsListItem>,
    ): CryptoRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { coin ->
                remoteKeyDao.getRemoteKeys(id = coin.id)
            }
    }

    private fun getRemoteKeyForLastItem(
        state: PagingState<Int, ResponseCoinsList.ResponseCoinsListItem>,
    ): CryptoRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { coin ->
                remoteKeyDao.getRemoteKeys(id = coin.id)
            }
    }
}