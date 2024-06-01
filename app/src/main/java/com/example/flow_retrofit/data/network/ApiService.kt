package com.example.flow_retrofit.data.network

import com.example.flow_retrofit.data.model.ResponseCoinsList
import com.example.flow_retrofit.utils.Constants.Companion.API_KEY_QUERY
import com.example.flow_retrofit.utils.Constants.Companion.API_KEY_VALUE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets?sparkline=true&order=market_cap_desc")
//    @Headers("$API_KEY_HEADER:$API_KEY_VALUE")
    suspend fun getCoinsList(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY_VALUE,
    ): Response<ResponseCoinsList>

}