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

    private val _user = MutableLiveData<UserInfo>()
    val user: LiveData<UserInfo> = _user

    private fun updateUser(user: UserInfo) {
        _user.value = user
    }

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private fun updateError(errorMsg: String) {
        _errorMsg.value = errorMsg
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    @SuppressLint("CheckResult")
    fun getUserInfo() {
        apiRepository.getUserInfo(userLogin!!).compose(NetworkScheduler.singleCompose())
            .subscribe({ userInfo ->
                updateUser(userInfo)
            }, {
                it.message?.let { it1 -> updateError(it1) }
            })
    }

}