package com.example.flow_retrofit.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flow_retrofit.data.model.CryptoRemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKeys: List<CryptoRemoteKey>)

    @Query("SELECT * FROM coins_remote_key where coinId = :id")
    fun getRemoteKeys(id: String): CryptoRemoteKey

    @Query("Delete from coins_remote_key")
    suspend fun deleteRemoteKeys()


}