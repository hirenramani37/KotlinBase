package com.your_app_directory_name.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.common.base.BaseActivity
import com.common.data.database.entities.UserLocal
import com.common.utils.EventBus
import com.your_app_directory_name.R
import com.your_app_directory_name.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEventBus()
        setUpUi()
        setUpObserver()

        viewModel.callApi()

        EventBus.publish(Intent())
        EventBus.publish(0)

        startActivity(Intent(this, RefreshTokenActivity::class.java))
    }

    private fun initEventBus() {
        EventBus.subscribe<Intent>(listSubscription) {

        }

        EventBus.subscribe<Int>(listSubscription) {

        }
    }

    private fun setUpUi() {
        binding.textMessage.text = "Activity Main Binding"
        binding.textMessage.setOnClickListener {
            viewModel.insertUser(UserLocal().apply { displayAlias = "Rohan" })
        }

    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.userInfoError.observe(this) { it.printStackTrace() }

        viewModel.userInfo.observe(this) {
            Timber.e("List of Users: ${it.size}")
        }

        viewModel.localUser?.observe(this) {
            Timber.e("Local Users: ${it.size}")
        }
    }
}