package com.jiwei.baselibrary.inject.module

import android.app.Activity
import android.content.Context
import com.jiwei.baselibrary.common.BaseApplication
import com.jiwei.baselibrary.inject.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by 18099 on 2018/12/19.
 */
@Module
class ActivityModule(private val activity: Activity){
    @ActivityScope
    @Provides
    fun provideActivity():Activity{
        return activity
    }
}