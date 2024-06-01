package com.example.flow_retrofit.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.flow_retrofit.database.CryptoDatabase
import com.example.flow_retrofit.model.ResponseCoinsList
import com.example.flow_retrofit.network.ApiService
import com.example.flow_retrofit.paging.CryptoPagingSource
import com.example.flow_retrofit.paging.CryptoRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class ApiRepository @Inject constructor(
    private val apiService: ApiService,
    private val cryptoDatabase: CryptoDatabase,
) {

    /* suspend fun getCoinList(vsCurrency: String) = flow {
         emit(DataStatus.loading())
         val result = apiService.getCoinsList(
             vsCurrency = vsCurrency,
             perPage = params.loadSize,
             page = position
         )
         when (result.code()) {
             200 -> {
                 Log.e("TAG444", "getCoinList: ${result.body()}")
                 emit(DataStatus.success(result.body()))
             }

             400 -> {
                 emit(DataStatus.error(result.message()))
             }

             500 -> {
                 emit(DataStatus.error(result.message()))
             }
         }
     }.catch {
         emit(DataStatus.error(it.message.toString()))
     }.flowOn(Dispatchers.IO)
 */

    fun getCoinListPager(): Flow<PagingData<ResponseCoinsList.ResponseCoinsListItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 30
        ),
        remoteMediator = CryptoRemoteMediator(apiService, cryptoDatabase),
        pagingSourceFactory = { cryptoDatabase.getCryptoDao().getCryptoCoins() }
//        pagingSourceFactory = { CryptoPagingSource(apiService) }
    ).flow
}