package com.example.flow_retrofit.utils

import android.icu.text.DecimalFormat
import android.view.View
import androidx.paging.LoadState

private val formatTwo = DecimalFormat("##.##")
private val formatThree = DecimalFormat("##.###")

fun Double.roundToTwoDecimals() = formatTwo.format(this).toString()
fun Double.roundToThreeDecimals() = formatThree.format(this).toString()

fun List<Double?>?.toDoubleToFloat(): List<Pair<String, Float>> {
    return this!!.map {
        val f = it!!.toFloat()
        val s = it.toString()
        Pair(s, f)
    }
}

fun View.isVisible(isShowLoading: Boolean, container: View) {
    if (isShowLoading) {
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    } else{
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}