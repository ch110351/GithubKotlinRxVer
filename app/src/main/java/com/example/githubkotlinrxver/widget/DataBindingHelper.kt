package com.example.githubkotlinrxver.widget

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.githubkotlinrxver.R

class DataBindingHelper {
    companion object {
        /**
         * 讀取user avatar url
         */
        @BindingAdapter("url_img")
        @JvmStatic
        fun imgFromUrl(imageView: ImageView, url: String?) {
<<<<<<< HEAD

            Glide.with(imageView.context)
                .load(url).apply(RequestOptions().circleCrop())
                .placeholder(R.drawable.avatar_def)
                .error(R.drawable.avatar_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)

//            GlobalScope.launch(Dispatchers.Main) {
//                Glide.with(imageView.context)
//                        .load(url).apply(RequestOptions().circleCrop())
//                        .placeholder(R.drawable.avatar_def)
//                        .error(R.drawable.avatar_error)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(imageView)
//            }
=======
            url?.let {
                Glide.with(imageView.context)
                    .load(it).apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.avatar_def)
                    .error(R.drawable.avatar_error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            }
>>>>>>> 5ce2953fc761a61f9dc4554d769a3616078a7288
        }
    }
}