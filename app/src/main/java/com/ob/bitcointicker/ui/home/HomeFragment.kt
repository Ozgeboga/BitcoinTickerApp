package com.ob.bitcointicker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ob.bitcointicker.R
import com.ob.bitcointicker.data.db.Coin
import com.ob.bitcointicker.databinding.FragmentHomeBinding
import com.ob.bitcointicker.utils.DialogUtil.warning
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
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater , container, false).apply {
            vm = viewModel
        }
        setListeners()
        showError()
        fetchCoins()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setListeners(){
        binding.coinSearchView.setOnQueryTextListener(onQueryTextChangeListener)
    }


    private val onQueryTextChangeListener : SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query : String?): Boolean  = false

        override fun onQueryTextChange(query : String?): Boolean {
            viewModel.searchCoinsOnQueryChange(query)
            return false
        }
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

    private fun showError(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.onError.collect{
                    binding.apply {
                        homeMainLayout.warning(resources.getString(R.string.fail_message))
                    }
                }
            }
        }
    }

    private fun setAdapter(coins : List<Coin>){
        adapter = CoinListAdapter(coins){
             navigateToDetail(it)
        }
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.apply {
            coinsList.adapter = adapter
            coinsList.layoutManager = layoutManager
        }
    }

    private fun navigateToDetail(coin  : Coin){
        val action = HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment(coin)
        findNavController().navigate(action)
    }
}