package com.ob.bitcointicker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ob.bitcointicker.data.app_db.Coin
import com.ob.bitcointicker.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter : CoinListAdapter
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater , container, false).apply {
            vm = viewModel
        }
        fetchCoins()
        fetchCoinDetails()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun fetchCoins(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.coinList.collect{
                    setAdapter(it)
                    adapter.update(it)
                }
            }
        }
    }

    private fun setAdapter(coins : List<Coin>){
        adapter = CoinListAdapter(coins){
            it.id?.let { id -> viewModel.getCoinDetail(id) }
        }
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.coinsList.adapter = adapter
        binding.coinsList.layoutManager = layoutManager
    }


    private fun fetchCoinDetails(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.coinDetails.collect{
                    //TODO(navigate to detail)
                }
            }
        }
    }
}