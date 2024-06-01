package com.example.flow_retrofit.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flow_retrofit.data.model.ResponseCoinsList
import com.example.flow_retrofit.data.network.ApiService

class CryptoPagingSource(private val apiService: ApiService) :
    PagingSource<Int, ResponseCoinsList.ResponseCoinsListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseCoinsList.ResponseCoinsListItem> {
        return try {

            val position = params.key ?: 1
            val response = apiService.getCoinsList(
                vsCurrency = "inr",
                perPage = params.loadSize,
                page = position
            ).body()?.map {
                it
            } ?: emptyList()

            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1 ,
                nextKey = if (response.isEmpty()) null else position + (params.loadSize / 10)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseCoinsList.ResponseCoinsListItem>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

}