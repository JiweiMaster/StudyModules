package com.jiwei.baselibrary.inject.component

import android.content.Context
import com.jiwei.baselibrary.common.BaseApplication
import com.jiwei.baselibrary.inject.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by 18099 on 2018/12/19.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun getContext():Context
}