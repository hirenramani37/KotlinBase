package com.your_app_directory_name.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.data.network.model.UserInfo
import kotlinx.coroutines.launch

class MainFragmentViewModel : BaseViewModel() {
    private val _mtUserInfo = MutableLiveData<List<UserInfo>>()
    val userInfo: LiveData<List<UserInfo>> = _mtUserInfo

    private val _mtUserInfoError = MutableLiveData<Throwable>()
    val mtUserInfoError: LiveData<Throwable> = _mtUserInfoError

    fun getUserInfoFromMT() {
        viewModelScope.launch {
            val mtUser = api1Repository.getUsers()
            processDataEvent(mtUser, onError = {
                _mtUserInfoError.postValue(it)
            }) {
                _mtUserInfo.postValue(it)
            }
        }
    }

}