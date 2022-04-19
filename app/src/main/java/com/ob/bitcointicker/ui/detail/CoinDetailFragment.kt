package com.ob.bitcointicker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ob.bitcointicker.databinding.FragmentDetailBinding
import com.ob.bitcointicker.ui.home.HomeFragmentDirections

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
        _binding = FragmentDetailBinding.inflate(inflater , container ,false).apply {
            coinDetail = args.details
        }
        setListeners()
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setListeners(){
        binding.toolbar.setNavigationOnClickListener {
            navigateBack()
        }
    }

    private fun navigateBack(){
        findNavController().navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToHomeFragment())
    }

}