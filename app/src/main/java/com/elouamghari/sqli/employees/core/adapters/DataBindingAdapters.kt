package com.elouamghari.sqli.employees.core.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DataBindingAdapters {
    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view).load(it).into(view)
            }
        }
    }
}