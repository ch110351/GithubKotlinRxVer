package com.example.githubkotlinrxver.common

import android.app.Application
import android.content.Context
import com.example.githubkotlinrxver.di.factoryModule
import com.example.githubkotlinrxver.di.repositoryModule
import com.example.githubkotlinrxver.di.userListSourceFactory
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        appContext = applicationContext
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    factoryModule,
                    repositoryModule,
                    userListSourceFactory
                )
            )
        }
    }
}
