package com.example.githubkotlinrxver.widget

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.githubkotlinrxver.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataBindingHelper {
    companion object {
        /**
         * 讀取user avatar url
         */
        @BindingAdapter("url_img")
        @JvmStatic
        fun imgFromUrl(imageView: ImageView, url: String?) {
            GlobalScope.launch(Dispatchers.Main) {
                Glide.with(imageView.context)
                        .load(url).apply(RequestOptions().circleCrop())
                        .placeholder(R.drawable.avatar_def)
                        .error(R.drawable.avatar_error)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView)
            }
        }
    }
}