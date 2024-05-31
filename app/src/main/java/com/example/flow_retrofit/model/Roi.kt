package com.example.flow_retrofit.model


import com.google.gson.annotations.SerializedName

data class Roi(
    @SerializedName("currency")
    var currency: String,
    @SerializedName("percentage")
    var percentage: Double,
    @SerializedName("times")
    var times: Double
)