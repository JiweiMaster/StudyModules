package com.jiwei.usercenter.injection.component

import com.jiwei.baselibrary.inject.PerActivityScope
import com.jiwei.baselibrary.inject.component.ActivityComponent
import com.jiwei.usercenter.injection.module.UserModule
import com.jiwei.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by 18099 on 2018/12/19.
 */
@PerActivityScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
}