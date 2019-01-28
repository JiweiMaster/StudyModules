package com.jiwei.baselibrary.common

import android.app.Application
import android.content.Context
import com.jiwei.baselibrary.inject.component.AppComponent
import com.jiwei.baselibrary.inject.component.DaggerAppComponent
import com.jiwei.baselibrary.inject.module.AppModule

/**
 * Created by 18099 on 2018/12/19.
 */
class BaseApplication: Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        context = this
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}