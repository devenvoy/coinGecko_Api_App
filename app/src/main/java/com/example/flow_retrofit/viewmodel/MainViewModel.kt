package com.example.flow_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.flow_retrofit.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MainViewModel
@Inject constructor(
    repository: ApiRepository,
) : ViewModel() {

//    private val _coinList =
//        MutableLiveData<DataStatus<List<ResponseCoinsList.ResponseCoinsListItem>>>()
//
//    val coinsList: LiveData<DataStatus<List<ResponseCoinsList.ResponseCoinsListItem>>>
//        get() = _coinList

//    fun getCoinList(vs_currency: String) =
//        viewModelScope.launch {
//            repository.getCoinList(vs_currency).collect {
//                _coinList.value = it
//            }
//        }

//    val CryptoList = PagingData<ResponseCoinsListItem>()

    val cryptoList = repository.getCoinListPager().cachedIn(viewModelScope)


}