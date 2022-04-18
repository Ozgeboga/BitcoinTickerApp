package com.ob.bitcointicker.utils

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import coil.load
import com.ob.bitcointicker.R

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

    @JvmStatic
    @BindingAdapter("descriptionText")
    fun setDescriptionText(view : TextView , text : String){
        view.text = text.substringBefore(".")
    }

    @JvmStatic
    @BindingAdapter("currentPriceText")
    fun setCurrentPriceText(view : TextView , price : Any){
        view.text = "$$price"
    }

    @JvmStatic
    @BindingAdapter("priceChangePercentage")
    fun setPriceChangePercentageText(view : TextView , percentage : Double){
        view.text = "% $percentage"
        if(percentage < 0) {
            view.setTextAppearance(R.style.Decreased)
        }else{
            view.setTextAppearance(R.style.Increased)
        }
    }
}