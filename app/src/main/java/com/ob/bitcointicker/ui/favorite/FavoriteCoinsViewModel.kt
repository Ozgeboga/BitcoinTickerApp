package com.ob.bitcointicker.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ob.bitcointicker.data.db.Coin
import com.ob.bitcointicker.data.db.FavoriteCoinDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCoinsViewModel @Inject constructor(
    private val favoriteCoinDataSource: FavoriteCoinDataSource
) : ViewModel() {

    private val _favCoinList = MutableSharedFlow<List<Coin>>()
    val favCoinList : SharedFlow<List<Coin>> = _favCoinList

    init {
        getFavoriteCoins()
    }

    private fun getFavoriteCoins(){
        viewModelScope.launch (Dispatchers.IO){
            _favCoinList.emit(favoriteCoinDataSource.getFavoriteCoins())
        }
    }
}