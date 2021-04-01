package com.example.githubkotlinrxver.widget

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class DataBindingAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val itemClick: ItemClick<T>? = null
) :
    ListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) =
        holder.bind(getItem(position))
}

class DataBindingViewHolder<T>(
    private val binding: ViewDataBinding,
    private val itemClick: ItemClick<T>? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    constructor(binding: ViewDataBinding) : this(binding, null)

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        if (itemClick != null)
            binding.setVariable(BR.itemclick, itemClick)

        binding.executePendingBindings()
    }
}

