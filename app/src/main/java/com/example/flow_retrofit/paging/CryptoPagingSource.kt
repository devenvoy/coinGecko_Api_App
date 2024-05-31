package com.example.flow_retrofit.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flow_retrofit.model.ResponseCoinsListItem
import com.example.flow_retrofit.network.ApiService

class CryptoPagingSource(private val apiService: ApiService) :
    PagingSource<Int, ResponseCoinsListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseCoinsListItem> {
        return try {

            val position = params.key ?: 1
            val response = apiService.getCoinsList(
                vsCurrency = "inr",
                perPage = params.loadSize,
                page = position
            )

            LoadResult.Page(
                data = response.body()!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == 299) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseCoinsListItem>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

}