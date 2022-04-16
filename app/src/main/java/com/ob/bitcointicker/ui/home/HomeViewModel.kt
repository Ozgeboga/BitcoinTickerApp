package com.ob.bitcointicker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ob.bitcointicker.api.Result
import com.ob.bitcointicker.data.app_db.Coin
import com.ob.bitcointicker.data.app_db.CoinListDataSource
import com.ob.bitcointicker.data.model.CoinListResponse
import com.ob.bitcointicker.data.repository.BitcoinTickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val repository : BitcoinTickerRepository ,
    private val coinListDataSource : CoinListDataSource
        ) : ViewModel() {

        private val _coinList = MutableSharedFlow<ArrayList<CoinListResponse>>()
        val coinList : SharedFlow<ArrayList<CoinListResponse>> = _coinList

    init {
        getCoinList()
    }

        private fun getCoinList(){
            viewModelScope.launch {
                val result = Result.of { repository.makeCoinListRequest("usd") }
                when(result){
                    is Result.Error ->{

                    }
                    is Result.Success ->{
                        val filteredCoinList = result.data.filter {
                            it.symbol.isEmpty().not()
                        }.map { item ->
                            Coin(
                                item.symbol ,
                                item.id
                            )
                        }

                        coinListDataSource.insertIntoDB(filteredCoinList)

                        _coinList.emit(result.data)
                    }
                }
            }
        }
}