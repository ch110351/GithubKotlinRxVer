package com.example.githubkotlinrxver.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.githubkotlinrxver.R
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.widget.DataBindingPagingAdapter
import com.example.githubkotlinrxver.widget.ItemClick

class UserListAdapter(itemClick: ItemClick<UserListItem>?) :
    DataBindingPagingAdapter<UserListItem>(
        DiffCallback(), itemClick
    ) {
    constructor() : this(null)

    class DiffCallback : DiffUtil.ItemCallback<UserListItem>() {

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: UserListItem,
            newItem: UserListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: UserListItem,
            newItem: UserListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_user
}