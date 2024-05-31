package com.example.flow_retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow_retrofit.adapter.CryptoAdapter
import com.example.flow_retrofit.adapter.LoaderAdpater
import com.example.flow_retrofit.databinding.FragmentHomeBinding
import com.example.flow_retrofit.paging.CryptoPagingAdapter
import com.example.flow_retrofit.utils.DataStatus
import com.example.flow_retrofit.utils.isVisible
import com.example.flow_retrofit.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModels: MainViewModel by viewModels()

    private lateinit var cryptoAdapter: CryptoAdapter

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

            viewModels.CryptoList.collect {

                pagingAdapter.submitData(lifecycle = lifecycle, pagingData = it)

            }
        }


    }

}