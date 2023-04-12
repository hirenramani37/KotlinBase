package com.your_app_directory_name.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.network.model.ResponseUser
import com.common.data.network.model.request.ReqLogin
import kotlinx.coroutines.launch
import timber.log.Timber

class RefreshTokenActivityViewModel : BaseViewModel() {
    private val _login = SingleLiveEvent<ResponseUser>()
    val login: LiveData<ResponseUser> = _login
    private val _loginError = MutableLiveData<Throwable>()
    val loginError: LiveData<Throwable> = _loginError

    fun login(reqLogin: ReqLogin) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(api2Repository.login(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _login.postValue(it)
            }
        }
    }

    fun dummy() {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(api2Repository.dummy(), onError = {
                it.printStackTrace()
            }) {
                Timber.d(it.toString())
            }
        }
    }
}