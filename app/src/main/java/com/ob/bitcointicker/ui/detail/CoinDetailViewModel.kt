package com.ob.bitcointicker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ob.bitcointicker.api.Result
import com.ob.bitcointicker.data.db.Coin
import com.ob.bitcointicker.data.db.FavoriteCoinDataSource
import com.ob.bitcointicker.data.model.CoinDetailResponse
import com.ob.bitcointicker.data.repository.BitcoinTickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel  @Inject constructor (
    private val repository : BitcoinTickerRepository,
    private val favoriteCoinDataSource : FavoriteCoinDataSource
) : ViewModel(){

    private val _coinDetails = MutableSharedFlow<CoinDetailResponse>()
    val coinDetails : SharedFlow<CoinDetailResponse> = _coinDetails

    private val _onError = MutableSharedFlow<Boolean>()
    val onError : SharedFlow<Boolean> = _onError


    fun getCoinDetail(id : String){
        viewModelScope.launch {
            val result = Result.of { repository.makeCoinDetailRequest(id) }
            when(result) {
                is Result.Success ->{
                    _coinDetails.emit(result.data)
                }
                is Result.Error -> {
                    _onError.emit(true)
                }
            }
        }
    }

    fun saveFavCoin(coin : Coin){
        coin.favorite = true
        viewModelScope.launch {
            favoriteCoinDataSource.insertIntoDB(coin)
        }
    }

}