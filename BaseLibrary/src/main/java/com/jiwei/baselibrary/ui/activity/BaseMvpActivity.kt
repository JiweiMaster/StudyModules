package com.jiwei.baselibrary.ui.activity

import android.os.Bundle
import android.os.Handler
import android.support.annotation.UiThread
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

    lateinit var errorDialog:QMUITipDialog

    override fun showLoading() {
        mDialog.showLoading()
    }

    override fun hideLoading() {
        mDialog.hideLoading()
    }

    override fun onError() {
        errorDialog.show()
        Handler().postDelayed({
            runOnUiThread {
                errorDialog.dismiss()
            }
        },2000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        mDialog  = LoadingDialog.create(this)
        //错误信息的QMUITipDialog
        errorDialog = QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("发送失败").create()
    }

    private fun initActivityInjection() {
        //直接点入appComponent的局部变量
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this)).build()
    }
}