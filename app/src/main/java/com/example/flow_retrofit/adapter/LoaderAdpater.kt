package com.example.flow_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_retrofit.databinding.LoaderItemBinding

class LoaderAdpater : LoadStateAdapter<LoaderAdpater.LoaderViewHolder>() {
    class LoaderViewHolder(private val binding: LoaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(loadState: LoadState) {
            binding.pBarLoading.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            LoaderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

}