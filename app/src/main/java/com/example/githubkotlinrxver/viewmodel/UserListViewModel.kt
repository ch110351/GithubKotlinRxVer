package com.example.githubkotlinrxver.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.http.api.USER_INITIAL_KEY
import com.example.githubkotlinrxver.http.api.USER_PAGE_SIZE
import com.example.githubkotlinrxver.paging.UserListSourceFactory
import io.reactivex.disposables.CompositeDisposable


class UserListViewModel(userListSourceFactory: UserListSourceFactory) : ViewModel() {
    private val disposables = CompositeDisposable()

    //var pagedList: LiveData<PagedList<UserListItem>> = userListSourceFactory.toLiveData(20, null)
    val pagedList: LiveData<PagedList<UserListItem>>

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(USER_PAGE_SIZE)
            .setPageSize(USER_PAGE_SIZE)
            .setPrefetchDistance(10)
            .build()

        pagedList = LivePagedListBuilder(userListSourceFactory, config)
            .setInitialLoadKey(USER_INITIAL_KEY)
            .build()

    }
}