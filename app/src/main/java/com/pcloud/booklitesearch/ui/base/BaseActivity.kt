package com.pcloud.booklitesearch.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {
    lateinit var viewDataBinding:T

    /**
     * get Activity Layout Resource Id
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }
}