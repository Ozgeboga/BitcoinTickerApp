package com.ob.bitcointicker.ui.detail

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
import androidx.navigation.fragment.navArgs
import com.ob.bitcointicker.databinding.FragmentDetailBinding
import com.ob.bitcointicker.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private val viewModel: CoinDetailViewModel by viewModels()
    private val args by navArgs<CoinDetailFragmentArgs>()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater , container ,false)
        getCoinDetails(args.id)
        fetchCoinDetails()
        setListeners()
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getCoinDetails(id : String){
        viewModel.getCoinDetail(id)
    }

    private fun setListeners(){
        binding.toolbar.setNavigationOnClickListener {
            navigateBack()
        }
    }

    private fun fetchCoinDetails(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.coinDetails.collect{
                    binding.apply {
                        coinDetail = it
                        coinDescription.text = it.description.en.substringBefore('.')
                    }
                }
            }
        }
    }

    private fun navigateBack(){
        findNavController().navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToHomeFragment())
    }

}