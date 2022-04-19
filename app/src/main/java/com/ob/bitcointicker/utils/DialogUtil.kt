package com.ob.bitcointicker.utils

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ob.bitcointicker.R

object DialogUtil {

    fun View.warning(warningMessage: Any, duration: Int = Snackbar.LENGTH_LONG, color: Int = R.color.bannerColor) {
        val message: String = when (warningMessage) {
            is Int -> resources.getString(warningMessage)
            is String -> warningMessage
            else -> ""
        }
        val snackbar = Snackbar.make(this, message, duration)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, color))
        val snackTextView: TextView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text)
        snackTextView.maxLines = 3
        snackbar.show()
    }
}