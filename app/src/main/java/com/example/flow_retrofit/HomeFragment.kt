package com.example.flow_retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.example.flow_retrofit.adapter.LoaderAdpater
import com.example.flow_retrofit.databinding.FragmentHomeBinding
import com.example.flow_retrofit.paging.CryptoPagingAdapter
import com.example.flow_retrofit.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModels: MainViewModel by viewModels()

//    private lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        cryptoAdapter = CryptoAdapter()
//        binding.recyclerView.adapter = cryptoAdapter

        /* lifecycleScope.launch {
             binding.apply {
                 viewModels.getCoinList("inr")
                 viewModels.coinsList.observe(viewLifecycleOwner) {
                     when (it.status) {
                         DataStatus.Status.LOADING -> {
                             pBarLoading.isVisible(true, recyclerView)
                         }

                         DataStatus.Status.SUCCESS -> {
                             pBarLoading.isVisible(false, recyclerView)
                             cryptoAdapter.differ.submitList(
                                 it.data
                             )
                         }

                         DataStatus.Status.ERROR -> {
                             pBarLoading.isVisible(false, recyclerView)
                             Toast.makeText(
                                 requireContext(),
                                 "Something Went Wrong",
                                 Toast.LENGTH_SHORT
                             ).show()
                         }
                     }
                 }
             }*/
//        }

        val pagingAdapter = CryptoPagingAdapter()
        binding.recyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = LoaderAdpater(),
            footer = LoaderAdpater()
        )

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.pBarLoading.isVisible = loadStates.refresh is LoadState.Loading
                binding.errorMsg.isVisible = loadStates.refresh is LoadState.Error
            }
        }

        lifecycleScope.launch {
            viewModels.cryptoList.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }


        binding.imgCross.setOnClickListener {
            pagingAdapter.refresh()
        }


//            viewModels.CryptoList.collect {
//                pagingAdapter.submitData(lifecycle = lifecycle, pagingData = it)
//            }


    }

}