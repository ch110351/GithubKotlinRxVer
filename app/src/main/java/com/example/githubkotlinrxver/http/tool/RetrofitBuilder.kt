package com.example.githubkotlinrxver.http.tool

import com.example.githubkotlinrxver.common.Constant.Companion.GITHUB_API_TOKEN
import com.example.githubkotlinrxver.common.Constant.Companion.GITHUB_USER
import com.example.githubkotlinrxver.common.Constant.Companion.HOST_URL
import com.example.githubkotlinrxver.http.api.ApiService
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val TIME_OUT = 30L

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientBuilder.build())
    }

    private val okHttpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .baseUrl(HOST_URL)
            .build()
            .create(ApiService::class.java)
    }

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            //Github api Basic Authentication　
            request.addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader(
                    "Authorization",
                    Credentials.basic(GITHUB_USER, GITHUB_API_TOKEN)
                )
            return chain.proceed(request.build())
        }

    }
}