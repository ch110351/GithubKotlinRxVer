package com.example.githubkotlinrxver.repository

import com.example.githubkotlinrxver.data.ApiResponse
import com.example.githubkotlinrxver.data.UserInfo
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.http.tool.RetrofitBuilder.apiService
import io.reactivex.Single
import retrofit2.Response

interface ApiRepository {
    //API
    fun getUserList(since: Long): Single<List<UserListItem>>
    fun getUserInfo(login: String): Single<UserInfo>
}

class ApiRepositoryImpl : ApiRepository {
    override fun getUserList(since: Long): Single<List<UserListItem>> {
        return apiService.getUsersList(since).flatMap { response ->
            if (response.isSuccessful && response.body() != null) {
                Single.just(response.body())
            } else {
                Single.error(Throwable(response.errorBody()?.string()))
            }
        }
    }

    override fun getUserInfo(login: String): Single<UserInfo> {
        return apiService.getUserInfo(login).flatMap { response ->
            if (response.isSuccessful && response.body() != null) {
                Single.just(response.body())
            } else {
                Single.error(Throwable(response.errorBody()?.string()))
            }
        }
    }
}