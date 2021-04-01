package com.example.githubkotlinrxver.widget

import android.widget.ImageView
import android.widget.TextView
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
//        @BindingAdapter("url_img")
//        @JvmStatic
//        fun imgFromUrl(imageView: ImageView, url: String) {
//            GlobalScope.launch(Dispatchers.Main) {
//                Glide.with(imageView.context)
//                    .load(url).apply(RequestOptions().circleCrop())
//                    .error(R.drawable.no_image_icon)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imageView)
//            }
//        }
//
//        @BindingAdapter("text")
//        @JvmStatic
//        fun setTextView(textView: TextView, text: String) {
//            GlobalScope.launch(Dispatchers.Main) {
//                if (text.isNotEmpty())
//                    textView.text = text
//                else
//                    textView.text = textView.context.resources.getString(R.string.no_rest_info)
//            }
//        }
    }
}