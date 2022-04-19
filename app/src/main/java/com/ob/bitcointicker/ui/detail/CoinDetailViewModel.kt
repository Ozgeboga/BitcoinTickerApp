package com.ob.bitcointicker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ob.bitcointicker.api.Result
import com.ob.bitcointicker.data.model.CoinDetailResponse
import com.ob.bitcointicker.data.repository.BitcoinTickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel  @Inject constructor (
    private val repository : BitcoinTickerRepository) : ViewModel(){

    private val _coinDetails = MutableSharedFlow<CoinDetailResponse>()
    val coinDetails : SharedFlow<CoinDetailResponse> = _coinDetails


    fun getCoinDetail(id : String){
        viewModelScope.launch {
            val result = Result.of { repository.makeCoinDetailRequest(id) }
            when(result) {
                is Result.Success ->{
                    _coinDetails.emit(result.data)
                }
                is Result.Error -> {
                    //TODO()
                }
            }
        }
    }

}