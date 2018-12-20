package com.jiwei.usercenter.injection.module

import com.jiwei.usercenter.service.UserSevice
import com.jiwei.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by 18099 on 2018/12/19.
 */
@Module
class UserModule {
    //原本是need接口，无法直接通过注入返回。通过module提供一个是实现类的方法返回对象
    @Provides
    fun providerUserService(userServiceImpl: UserServiceImpl):UserSevice{
        return userServiceImpl
    }
}