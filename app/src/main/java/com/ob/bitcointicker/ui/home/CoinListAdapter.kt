package com.ob.bitcointicker.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ob.bitcointicker.data.app_db.Coin
import com.ob.bitcointicker.databinding.CoinListItemBinding

class CoinListAdapter (
    private var coins : List<Coin>,
    private val onItemClicked: (Coin) -> Unit
        ) : RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    fun update(coins: List<Coin>) {
        this.coins = coins
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CoinListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding){
            onItemClicked(coins[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(coins[position])
    }

    override fun getItemCount(): Int = coins.size


    class ViewHolder (
        private val binding: CoinListItemBinding,
        onItemClicked : (Int) -> Unit
            ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind (coin : Coin){
            binding.apply {
                this.coin = coin
            }
        }
    }

}