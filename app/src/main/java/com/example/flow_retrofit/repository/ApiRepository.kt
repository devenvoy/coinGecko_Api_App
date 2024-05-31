package com.example.flow_retrofit.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.flow_retrofit.network.ApiService
import com.example.flow_retrofit.paging.CryptoPagingSource
import com.example.flow_retrofit.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService,
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

    fun getCoinListPager() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 30
        ),
        pagingSourceFactory = { CryptoPagingSource(apiService) }
    ).flow
}