package com.example.githubkotlinrxver.di

import com.example.githubkotlinrxver.ui.adapter.paging.UserListSourceFactory
import com.example.githubkotlinrxver.repository.ApiRepository
import com.example.githubkotlinrxver.repository.ApiRepositoryImpl
//import com.example.githubkotlinrxver.repository.ApiRepositoryImpl
import com.example.githubkotlinrxver.viewmodel.UserInfoViewModel
import com.example.githubkotlinrxver.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val factoryModule = module {
    factory<ApiRepository> { (ApiRepositoryImpl()) }
    //single { ApiRepository() }
}

val repositoryModule = module {
    viewModel {
        UserListViewModel(userListSourceFactory = get())

    }
    viewModel {
        UserInfoViewModel(apiRepository = get())
    }
}

val userListSourceFactory = module {
    single { UserListSourceFactory(apiRepository = get()) }
}