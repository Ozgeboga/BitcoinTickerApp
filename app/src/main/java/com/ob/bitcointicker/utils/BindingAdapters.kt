package com.ob.bitcointicker.utils

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("queryTextListener")
    fun setQueryTextListener(searchView: SearchView, listener: SearchView.OnQueryTextListener) {
        searchView.setOnQueryTextListener(listener)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view  : ImageView, url : String?){
        view.load(url)
    }
}