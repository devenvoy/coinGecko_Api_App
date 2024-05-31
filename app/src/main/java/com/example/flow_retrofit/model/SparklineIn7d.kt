package com.example.flow_retrofit.model


import com.google.gson.annotations.SerializedName

data class SparklineIn7d(
    @SerializedName("price")
    var price: List<Double>
)