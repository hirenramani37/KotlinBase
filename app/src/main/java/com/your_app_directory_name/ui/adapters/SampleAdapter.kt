package com.your_app_directory_name.ui.adapters

import android.view.View
import androidx.databinding.ViewDataBinding
import com.common.base.BaseAdapter
import com.your_app_directory_name.R

/**
 * created by Nikul on 4/2/21
 */
class SampleAdapter : BaseAdapter<ViewDataBinding, String>(R.layout.list_item_spinner) {
    init {
        setItemPercentageWidth { 50 }
    }

    override fun setClickableView(binding: ViewDataBinding) = listOf<View>()

    override fun onBind(
        binding: ViewDataBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }

}