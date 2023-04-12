package com.your_app_directory_name.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.common.base.BaseActivity
import com.common.data.network.model.request.ReqLogin
import com.your_app_directory_name.R
import com.your_app_directory_name.databinding.ActivityRefreshTokenBinding
import timber.log.Timber

class RefreshTokenActivity :
    BaseActivity<ActivityRefreshTokenBinding>(R.layout.activity_refresh_token),
    View.OnClickListener {

    private val viewModel: RefreshTokenActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
        setUpObserver()
        clickListeners()
    }

    private fun setUpUi() {

    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }
        viewModel.appLoader.observe(this) { updateLoaderUI(it) }
        viewModel.loginError.observe(this) { it.printStackTrace() }

        viewModel.login.observe(this) {
            Timber.d("$it")
            pref.authToken = it.authToken
        }
    }

    private fun clickListeners() {
        binding.btnLogin.setOnClickListener(this)
        binding.btnDummy.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnLogin -> {
                val reqLogin = ReqLogin("new@mail.com", "123")
                viewModel.login(reqLogin)
            }
            R.id.btnDummy -> {
                viewModel.dummy()
            }
        }
    }
}