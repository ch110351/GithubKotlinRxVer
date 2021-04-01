package com.example.githubkotlinrxver.http.api


import com.example.githubkotlinrxver.data.UserInfo
import com.example.githubkotlinrxver.data.UserListItem
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val USER_PAGE_SIZE = 20
const val USER_INITIAL_KEY = 0L

interface ApiService {
    @GET("/users")
    fun getUsersList(
        @Query("since") since: Long,
        @Query("per_page") perPage: Int = USER_PAGE_SIZE
    ): Single<Response<List<UserListItem>>>

    @GET("users/{login}")
    fun getUserInfo(
        @Path("login") login: String
    ): Single<Response<UserInfo>>
}