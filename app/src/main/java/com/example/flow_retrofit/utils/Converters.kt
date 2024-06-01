package com.example.flow_retrofit.utils

import androidx.room.TypeConverter
import com.example.flow_retrofit.data.model.ResponseCoinsList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromRoi(value: ResponseCoinsList.ResponseCoinsListItem.Roi?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toRoi(value: String?): ResponseCoinsList.ResponseCoinsListItem.Roi? {
        val type: Type = object : TypeToken<ResponseCoinsList.ResponseCoinsListItem.Roi>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSparkline(value: ResponseCoinsList.ResponseCoinsListItem.SparklineIn7d?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSparkline(value: String?): ResponseCoinsList.ResponseCoinsListItem.SparklineIn7d? {
        val type: Type =
            object : TypeToken<ResponseCoinsList.ResponseCoinsListItem.SparklineIn7d>() {}.type
        return Gson().fromJson(value, type)
    }
}