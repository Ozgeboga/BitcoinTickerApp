package com.ob.bitcointicker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ob.bitcointicker.api.Result
import com.ob.bitcointicker.data.db.Coin
import com.ob.bitcointicker.data.db.CoinListDataSource
import com.ob.bitcointicker.data.repository.BitcoinTickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val repository : BitcoinTickerRepository ,
    private val coinListDataSource : CoinListDataSource
        ) : ViewModel() {

    private val _coinList = MutableSharedFlow<ArrayList<Coin>>()
    val coinList : SharedFlow<ArrayList<Coin>> = _coinList

    private val _onError = MutableSharedFlow<Boolean>()
    val onError : SharedFlow<Boolean> = _onError

     init {
         getCoinList()
     }

     private fun getCoinList(){
        viewModelScope.launch {
            val result = Result.of { repository.makeCoinListRequest() }
            when(result){
                is Result.Error ->{
                    _onError.emit(true)
                }
                is Result.Success ->{
                    val filteredCoinList = result.data.filter {
                        it.symbol.isEmpty().not()
                    }.map { item ->
                        Coin(
                            item.symbol ,
                            item.id ,
                            item.image.large,
                            item.name,
                            item.current_price ,
                            item.price_change_percentage_24h ,
                        false)
                    }
                    coinListDataSource.insertIntoDB(filteredCoinList)
                }
            }
        }
    }

    fun searchCoinsOnQueryChange(query : String?){
        viewModelScope.launch {
            query?.let {
                delay(250)
                searchCoins(query)
            }
        }
    }

    private fun searchCoins(searchQuery : String ) {
        viewModelScope.launch (Dispatchers.IO){
            _coinList.emit(coinListDataSource.findCoinsUsingParams(searchQuery) as ArrayList<Coin>)
        }
    }

}