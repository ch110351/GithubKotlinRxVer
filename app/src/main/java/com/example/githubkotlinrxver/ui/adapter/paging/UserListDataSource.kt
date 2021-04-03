package com.example.githubkotlinrxver.ui.adapter.paging

import android.annotation.SuppressLint
import android.os.Trace
import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.http.api.USER_INITIAL_KEY
import com.example.githubkotlinrxver.http.tool.NetworkScheduler
import com.example.githubkotlinrxver.repository.ApiRepository
import retrofit2.http.Tag

class UserListDataSource(private val apiRepository: ApiRepository) :
    ItemKeyedDataSource<Long, UserListItem>() {

    var count = 0

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<UserListItem>
    ) {
        apiRepository.getUserList(params.requestedInitialKey ?: USER_INITIAL_KEY)
            .compose(NetworkScheduler.singleCompose())
            .subscribe({ userList ->
                count++
                callback.onResult(userList)
            }, {

            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {
        /**
         * 作業題目限制只讀取100位users
         */
        if (count < 5) {
            apiRepository.getUserList(params.key)
                .compose(NetworkScheduler.singleCompose())
                .subscribe({ userList ->
                    count++
                    callback.onResult(userList)
                }, {

                })
        } else {
            /**
             * Stop, 題目限制
             */
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {

    }

    override fun getKey(item: UserListItem): Long {
        //利用id取得下一筆資料 API的since參數
        return item.id
    }
}