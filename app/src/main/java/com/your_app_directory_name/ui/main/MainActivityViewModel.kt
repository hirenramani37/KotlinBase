package com.your_app_directory_name.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.entities.UserLocal
import com.common.data.network.model.UserInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivityViewModel : BaseViewModel() {

    private val _userInfo = SingleLiveEvent<List<UserInfo>>()
    val userInfo: LiveData<List<UserInfo>> = _userInfo

    private val _userInfoError = MutableLiveData<Throwable>()
    val userInfoError: LiveData<Throwable> = _userInfoError

    var localUser: LiveData<List<UserLocal>>? = null

    init {
        localUser = dao.getMTUser()
    }

    fun callApi() {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(api1Repository.getUsers(), onError = {
                _userInfoError.postValue(it)
            }) {
                _userInfo.postValue(it)
            }
        }
    }

    fun insertUser(userLocal: UserLocal) {
        viewModelScope.launch {
            val insertedUser = dao.insertMTUser(userLocal)
            Timber.e("insertedUser : ${insertedUser.size}")
        }
    }

}