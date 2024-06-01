package com.example.flow_retrofit.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class ResponseCoinsList : ArrayList<ResponseCoinsList.ResponseCoinsListItem>() {
    @Entity(tableName = "crypto_coin")
    data class ResponseCoinsListItem(

        @PrimaryKey
        @SerializedName("id")
        var id: String,

        @SerializedName("ath")
        @ColumnInfo(name = "ath")
        var ath: Double,

        @SerializedName("ath_change_percentage")
        @ColumnInfo(name = "ath_change_percentage")
        var athChangePercentage: Double,

        @SerializedName("ath_date")
        @ColumnInfo(name = "ath_date")
        var athDate: String,

        @SerializedName("atl")
        @ColumnInfo(name = "atl")
        var atl: Double,

        @SerializedName("atl_change_percentage")
        @ColumnInfo(name = "atl_change_percentage")
        var atlChangePercentage: Double,

        @SerializedName("atl_date")
        @ColumnInfo(name = "atl_date")
        var atlDate: String,

        @SerializedName("circulating_supply")
        @ColumnInfo(name = "circulating_supply")
        var circulatingSupply: Double,

        @SerializedName("current_price")
        @ColumnInfo(name = "current_price")
        var currentPrice: Double,

        @SerializedName("fully_diluted_valuation")
        @ColumnInfo(name = "fully_diluted_valuation")
        var fullyDilutedValuation: Long?,

        @SerializedName("high_24h")
        @ColumnInfo(name = "high_24h")
        var high24h: Double,

        @SerializedName("image")
        @ColumnInfo(name = "image")
        var image: String,

        @SerializedName("last_updated")
        @ColumnInfo(name = "last_updated")
        var lastUpdated: String,

        @SerializedName("low_24h")
        @ColumnInfo(name = "low_24h")
        var low24h: Double,

        @SerializedName("market_cap")
        @ColumnInfo(name = "market_cap")
        var marketCap: Long,

        @SerializedName("market_cap_change_24h")
        @ColumnInfo(name = "market_cap_change_24h")
        var marketCapChange24h: Double,

        @SerializedName("market_cap_change_percentage_24h")
        @ColumnInfo(name = "market_cap_change_percentage_24h")
        var marketCapChangePercentage24h: Double,

        @SerializedName("market_cap_rank")
        @ColumnInfo(name = "market_cap_rank")
        var marketCapRank: Int,

        @SerializedName("max_supply")
        @ColumnInfo(name = "max_supply")
        var maxSupply: Double?,

        @SerializedName("name")
        @ColumnInfo(name = "name")
        var name: String,

        @SerializedName("price_change_24h")
        @ColumnInfo(name = "price_change_24h")
        var priceChange24h: Double,

        @SerializedName("price_change_percentage_24h")
        @ColumnInfo(name = "price_change_percentage_24h")
        var priceChangePercentage24h: Double,

        @SerializedName("roi")
        @ColumnInfo(name = "roi")
        var roi: Roi?,

        @SerializedName("sparkline_in_7d")
        @ColumnInfo(name = "sparkline_in_7d")
        var sparklineIn7d: SparklineIn7d,

        @SerializedName("symbol")
        @ColumnInfo(name = "symbol")
        var symbol: String,

        @SerializedName("total_supply")
        @ColumnInfo(name = "total_supply")
        var totalSupply: Double?,

        @SerializedName("total_volume")
        @ColumnInfo(name = "total_volume")
        var totalVolume: Double,
    ) {
        @Entity(tableName = "roi")
        data class Roi(
            @SerializedName("currency")
            var currency: String,
            @SerializedName("percentage")
            var percentage: Double,
            @SerializedName("times")
            var times: Double,
        )


        @Entity(tableName = "price_list")
        data class SparklineIn7d(
            @SerializedName("price")
            var price: List<Double>,
        )
    }
}