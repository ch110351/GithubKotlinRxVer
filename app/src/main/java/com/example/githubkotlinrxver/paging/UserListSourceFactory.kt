package com.example.githubkotlinrxver.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.repository.ApiRepository

class UserListSourceFactory(private val apiRepository: ApiRepository) :
    DataSource.Factory<Long, UserListItem>() {
    //private val sourceLiveData = MutableLiveData<DataSource<Long, UserListItem>>()

    override fun create(): DataSource<Long, UserListItem> {
        val source = UserListDataSource(apiRepository)
        //sourceLiveData.postValue(source)
        return source
    }
}