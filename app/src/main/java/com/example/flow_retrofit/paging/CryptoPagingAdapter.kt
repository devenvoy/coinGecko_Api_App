package com.example.flow_retrofit.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.flow_retrofit.R
import com.example.flow_retrofit.databinding.ItemBinding
import com.example.flow_retrofit.model.ResponseCoinsList
import com.example.flow_retrofit.utils.roundToTwoDecimals

class CryptoPagingAdapter :
    PagingDataAdapter<ResponseCoinsList.ResponseCoinsListItem, CryptoPagingAdapter.QuoteViewHolder>(diffCallback = Comparator()) {

    class QuoteViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResponseCoinsList.ResponseCoinsListItem) {
            binding.apply {

                pageNum.text = item.id

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

//                lineChart.gradientFillColors = intArrayOf(
//                    Color.parseColor("#2a9085"),
//                    Color.TRANSPARENT
//                )
//
//                lineChart.animation.duration = Constants.animationDuration
//
//                val listData = item.sparklineIn7d.price.toDoubleToFloat()
//                lineChart.animate(listData ?: emptyList())

            }
        }
    }

    override fun onBindViewHolder(
        holder: QuoteViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val newItem = payloads[0] as ResponseCoinsList.ResponseCoinsListItem
            holder.onBind(newItem)
        }
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        // getItem() will provide current data object based on position

        if (getItem(position) != null) {
            holder.onBind(getItem(position)!!)
        }
//        holder.setIsRecyclable(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class Comparator : DiffUtil.ItemCallback<ResponseCoinsList.ResponseCoinsListItem>() {
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

}