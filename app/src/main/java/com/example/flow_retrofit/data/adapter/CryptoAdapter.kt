package com.example.flow_retrofit.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.flow_retrofit.R
import com.example.flow_retrofit.databinding.ItemBinding
import com.example.flow_retrofit.data.model.ResponseCoinsList
import com.example.flow_retrofit.utils.roundToTwoDecimals

class CryptoAdapter() : RecyclerView.Adapter<CryptoAdapter.MyViewHolder>() {


    class MyViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResponseCoinsList.ResponseCoinsListItem) {
            binding.apply {
                tvName.text = item.name
                tvPrice.text = buildString {
                    append("₹ ")
                    append(item.currentPrice.roundToTwoDecimals())
                }
                tvSymbol.text = item.symbol.uppercase()

                imgCrypto.load(item.image) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.bitcoin_logo)
                    error(R.drawable.cross)
                }
//
//                lineChart.gradientFillColors = intArrayOf(
//                    Color.parseColor("#2a9085"),
//                    Color.TRANSPARENT
//                )
//
//                lineChart.animation.duration = animationDuration
//
//                val listData = item.sparklineIn7d.price.toDoubleToFloat()
//                lineChart.animate(listData)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseCoinsList.ResponseCoinsListItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseCoinsList.ResponseCoinsListItem,
            newItem: ResponseCoinsList.ResponseCoinsListItem,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseCoinsList.ResponseCoinsListItem,
            newItem: ResponseCoinsList.ResponseCoinsListItem,
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}