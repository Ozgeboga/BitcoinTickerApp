package com.ob.bitcointicker.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ob.bitcointicker.data.db.Coin
import com.ob.bitcointicker.databinding.FragmentFavoriteCoinsBinding
import com.ob.bitcointicker.ui.home.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteCoinsFragment : Fragment() {

    private val viewModel: FavoriteCoinsViewModel by viewModels()
    private var _binding : FragmentFavoriteCoinsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : CoinListAdapter
    private lateinit var lastCoin : Coin

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteCoinsBinding.inflate(layoutInflater , container , false)
        fetchFavCoins()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setNavListener(lastCoin : Coin){
        binding.toolbar.setNavigationOnClickListener {
            navigateToCoinDetail(lastCoin)
        }
    }

    private fun fetchFavCoins(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.favCoinList.collect{
                    setAdapter(it)
                    lastCoin = it.last()
                    setNavListener(lastCoin)
                }
            }
        }
    }

    private fun setAdapter(coins : List<Coin>){
        adapter = CoinListAdapter(coins){
            navigateToCoinDetail(it)
        }
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.apply {
            favCoinsList.adapter = adapter
            favCoinsList.layoutManager = layoutManager
        }
    }

    private fun navigateToCoinDetail(coin : Coin){
        val action = FavoriteCoinsFragmentDirections.actionFavoriteCoinsFragmentToCoinDetailFragment(coin)
        findNavController().navigate(action)
    }
}