package com.example.githubkotlinrxver.ui.adapter.paging

import androidx.paging.DataSource
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.repository.ApiRepository

class UserListSourceFactory(private val apiRepository: ApiRepository) :
    DataSource.Factory<Long, UserListItem>() {
    override fun create(): DataSource<Long, UserListItem> {
        return UserListDataSource(apiRepository)
    }
}