package com.jiwei.baselibrary.ui.activity

import android.os.Bundle
import android.os.Handler
import com.jiwei.baselibrary.common.BaseApplication
import com.jiwei.baselibrary.inject.component.ActivityComponent
import com.jiwei.baselibrary.inject.component.AppComponent
import com.jiwei.baselibrary.inject.component.DaggerActivityComponent
import com.jiwei.baselibrary.inject.module.ActivityModule
import com.jiwei.baselibrary.present.view.BasePresent
import com.jiwei.baselibrary.present.view.BaseView
import com.jiwei.baselibrary.widget.LoadingDialog
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import javax.inject.Inject

/**
 * Created by 18099 on 2018/12/17.
 */
open class BaseMvpActivity<T : BasePresent<*>> : BaseActivity(),BaseView {


    @Inject
    lateinit var mPresent : T

    lateinit var activityComponent: ActivityComponent

    lateinit var mDialog:LoadingDialog

    override fun showLoading() {
        mDialog.showLoading()
    }

    override fun hideLoading() {
        mDialog.hideLoading()
    }

    override fun onError() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        mDialog  = LoadingDialog.create(this)
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this)).build()
    }
}