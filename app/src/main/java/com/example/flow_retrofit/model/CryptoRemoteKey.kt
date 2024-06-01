package com.example.flow_retrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins_remote_key")
data class CryptoRemoteKey(

    @PrimaryKey(autoGenerate = false)
    var coinId: String,
    var prevPage: Int?,
    var nextPage: Int?,
) {
    constructor() : this("", 0, 0)
}