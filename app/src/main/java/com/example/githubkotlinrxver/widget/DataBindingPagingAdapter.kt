package com.example.githubkotlinrxver.widget


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class DataBindingPagingAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val itemClick: ItemClick<T>? = null
) :
    PagedListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int): Unit =
        position?.let {
            getItem(position)?.let { it1 -> holder.bind(it1) }
        }
}