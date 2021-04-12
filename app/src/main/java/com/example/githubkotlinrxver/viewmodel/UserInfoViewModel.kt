package com.example.githubkotlinrxver.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubkotlinrxver.data.UserInfo
import com.example.githubkotlinrxver.http.tool.NetworkScheduler
import com.example.githubkotlinrxver.repository.ApiRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * user info viewmodel
 */
class UserInfoViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    private val disposables = CompositeDisposable()
    //對應至api user login 參數
    var userLogin: String? = null

    private val userInfo = MutableLiveData<UserInfo>()
    val user: LiveData<UserInfo> = userInfo

    private fun updateUser(user: UserInfo) {
        userInfo.value = user
    }

    private val errorStr = MutableLiveData<String>()
    val errorMsg: LiveData<String> = errorStr

    private fun updateError(errorMsg: String) {
        errorStr.value = errorMsg
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    @SuppressLint("CheckResult")
    fun getUserInfo() {
        userLogin?.let { it ->
            apiRepository.getUserInfo(it).compose(NetworkScheduler.singleCompose())
                .subscribe({ userInfo ->
                    updateUser(userInfo)
                }, {
                    it.message?.let { it1 -> updateError(it1) }
                })
        }
    }

}