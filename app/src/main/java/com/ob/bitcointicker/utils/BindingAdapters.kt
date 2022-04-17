package com.ob.bitcointicker.utils

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("queryTextListener")
    fun setQueryTextListener(searchView: SearchView, listener: SearchView.OnQueryTextListener) {
        searchView.setOnQueryTextListener(listener)
    }
}