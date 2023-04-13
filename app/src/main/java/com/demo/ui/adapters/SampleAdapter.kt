package com.demo.ui.adapters

import android.view.View
import com.common.base.BaseAdapter
import com.common.data.network.model.data_class_exmple
import com.demo.R
import com.demo.databinding.ListItemSpinnerBinding


class SampleAdapter : BaseAdapter<ListItemSpinnerBinding, data_class_exmple.data_class_exmpleItem>(R.layout.list_item_spinner) {


    override fun setClickableView(binding: ListItemSpinnerBinding): List<View?> =
        listOf(binding.tvItemText)



    override fun onBind(
        binding: ListItemSpinnerBinding,
        position: Int,
        item: data_class_exmple.data_class_exmpleItem,
        payloads: MutableList<Any>?
    ) {
        binding.run {
            tvItemText.text = item.category
        }

    }

}