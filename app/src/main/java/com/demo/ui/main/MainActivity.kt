package com.demo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.common.base.BaseActivity
import com.common.data.database.entities.UserLocal
import com.common.utils.EventBus
import com.demo.databinding.ActivityMainBinding
import com.demo.ui.adapters.SampleAdapter
import com.your_app_directory_name.R
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var availableTaskAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEventBus()
        setUpUi()
        setUpObserver()
        availableTaskAdapter = SampleAdapter()
            binding.rvCompletedTasks.adapter = availableTaskAdapter
           viewModel.callApi()

        EventBus.publish(Intent())
        EventBus.publish(0)

//        startActivity(Intent(this, RefreshTokenActivity::class.java))
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
            availableTaskAdapter.addAll(it)
            Timber.e("List of Users: ${it.size}")
        }

        viewModel.localUser?.observe(this) {
            Timber.e("Local Users: ${it.size}")
            it.forEach {
                Timber.e("Local Users: ${it.displayAlias}")
                Timber.e("Local Users: ${it.id}")
                binding.textMessage.text = it.displayAlias?:"empty"

            }
        }
    }
}