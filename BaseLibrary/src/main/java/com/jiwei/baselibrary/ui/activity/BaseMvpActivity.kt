package com.jiwei.baselibrary.ui.activity

import com.jiwei.baselibrary.present.view.BasePresent
import com.jiwei.baselibrary.present.view.BaseView

/**
 * Created by 18099 on 2018/12/17.
 */
open class BaseMvpActivity<T : BasePresent<*>> : BaseActivity(),BaseView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }

    /**
     * 因为不确定传入的Present的类型是什么，所以我们传入一个泛型
     * 而不是具体的参数
     */
    lateinit var mPresent : T
}