package com.jiwei.usercenter.service.impl

import android.util.Log
import com.jiwei.baselibrary.data.net.RetrofitFactory
import com.jiwei.usercenter.data.api.UserApi
import com.jiwei.usercenter.data.procotol.LoginReq
import com.jiwei.usercenter.service.UserSevice
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by 18099 on 2018/12/17.
 */
class UserServiceImpl: UserSevice {
    override fun  register(mobile: String, password: String, vertifyCode: String): Observable<Boolean> {
        //发送一段网络请求

        Log.e("jiwie",""+mobile+""+password+""+vertifyCode)
        RetrofitFactory.instance.create(UserApi::class.java)
                .login(mobile,password,vertifyCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    val str: String = it.string().toString()
                    Log.e("jiwei",""+str)
                }, Consumer {
                    Log.e("jiwei",it.message)
                })
        return Observable.just(true)
    }
}