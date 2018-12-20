package com.jiwei.baselibrary.inject.component

import android.app.Activity
import com.jiwei.baselibrary.inject.ActivityScope
import com.jiwei.baselibrary.inject.module.ActivityModule
import dagger.Component

/**
 * Created by 18099 on 2018/12/19.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent{
    fun getActivity(): Activity
}