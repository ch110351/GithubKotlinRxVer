package com.example.githubkotlinrxver.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.http.api.USER_INITIAL_KEY
import com.example.githubkotlinrxver.http.tool.NetworkScheduler
import com.example.githubkotlinrxver.repository.ApiRepository

class UserListDataSource(private val apiRepository: ApiRepository) :
    ItemKeyedDataSource<Long, UserListItem>() {

    var count = 0
    var totalSize = 0

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<UserListItem>
    ) {
        apiRepository.getUserList(params.requestedInitialKey ?: USER_INITIAL_KEY)
            .compose(NetworkScheduler.singleCompose())
            .subscribe({ userList ->
                totalSize += userList.size
                count++
                Log.d("Wesley", "totalSize $totalSize")
                callback.onResult(userList)
            }, {
                // Log.d("Wesley", "loadInitial ${it.message}")
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {
        if (count < 5) {
            apiRepository.getUserList(params.key)
                .compose(NetworkScheduler.singleCompose())
                .subscribe({ userList ->
                    totalSize += userList.size
                    count++
                    Log.d("Wesley", "totalSize $totalSize")
                    callback.onResult(userList)
                }, {
                    //Log.d("Wesley", "loadAfter ${it.message}")
                    //show the error message (it.message)
                })
        } else {
            //STOP
        }


    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {

    }

    override fun getKey(item: UserListItem): Long {
        //利用id取得下一筆資料 API的since參數
        Log.d("Wesley", "id " + item.id)
        return item.id
    }
}