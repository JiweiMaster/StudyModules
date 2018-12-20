package com.jiwei.usercenter.service.impl

import android.util.Log
import com.jiwei.baselibrary.data.net.RetrofitFactory
import com.jiwei.baselibrary.ext.GsonUtil
import com.jiwei.usercenter.data.api.UserApi
import com.jiwei.usercenter.data.procotol.LoginReq
import com.jiwei.usercenter.data.procotol.LoginRes
import com.jiwei.usercenter.data.repository.UserRepository
import com.jiwei.usercenter.service.UserSevice
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by 18099 on 2018/12/17.
 */
class UserServiceImpl @Inject constructor(): UserSevice {
    @Inject
    lateinit var userRepository: UserRepository
    override fun  register(mobile: String, password: String, vertifyCode: String): Observable<LoginRes> {
        //发送一段网络请求
        return userRepository.login(mobile,password,vertifyCode)
    }
}