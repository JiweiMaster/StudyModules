package com.jiwei.baselibrary.common

import android.app.Application
import com.jiwei.baselibrary.inject.component.AppComponent
import com.jiwei.baselibrary.inject.component.DaggerAppComponent
import com.jiwei.baselibrary.inject.module.AppModule

/**
 * Created by 18099 on 2018/12/19.
 */
class BaseApplication: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}