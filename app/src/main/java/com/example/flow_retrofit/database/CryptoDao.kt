package com.example.flow_retrofit.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flow_retrofit.model.ResponseCoinsList

@Dao
interface CryptoDao {

    @Query("delete from crypto_coin")
    suspend fun deleteCryptoCoins()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCryptoCoins(coinsList: List<ResponseCoinsList.ResponseCoinsListItem>)

    @Query("select * from crypto_coin")
    fun getCryptoCoins(): PagingSource<Int, ResponseCoinsList.ResponseCoinsListItem>


}