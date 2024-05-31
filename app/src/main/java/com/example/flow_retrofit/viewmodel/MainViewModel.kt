package com.example.flow_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.room.util.recursiveFetchLongSparseArray
import com.example.flow_retrofit.model.ResponseCoinsListItem
import com.example.flow_retrofit.repository.ApiRepository
import com.example.flow_retrofit.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: ApiRepository,
) : ViewModel() {

    private val _coinList = MutableLiveData<DataStatus<List<ResponseCoinsListItem>>>()

    val coinsList: LiveData<DataStatus<List<ResponseCoinsListItem>>>
        get() = _coinList

//    fun getCoinList(vs_currency: String) =
//        viewModelScope.launch {
//            repository.getCoinList(vs_currency).collect {
//                _coinList.value = it
//            }
//        }

//    val CryptoList = PagingData<ResponseCoinsListItem>()

    val CryptoList = repository.getCoinListPager()


    fun getCryptoList() {
        viewModelScope.launch {
//            repository.getCoinListPager().collect{
////                CryptoList.value = it
//            }
        }
    }


}