package com.jiwei.baselibrary.inject.module

import android.content.Context
import com.jiwei.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by 18099 on 2018/12/19.
 */
@Module
class AppModule(private val application: BaseApplication) {
    @Singleton
    @Provides
    fun porvideContext():Context{
        return application
    }
}