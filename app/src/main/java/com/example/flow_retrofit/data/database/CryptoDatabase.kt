package com.example.flow_retrofit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flow_retrofit.data.model.CryptoRemoteKey
import com.example.flow_retrofit.data.model.ResponseCoinsList
import com.example.flow_retrofit.utils.Converters

@Database(
    entities = [
        ResponseCoinsList.ResponseCoinsListItem::class,
        CryptoRemoteKey::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun getCryptoDao(): CryptoDao
    abstract fun getRemoteKeyDao(): RemoteKeyDao
}