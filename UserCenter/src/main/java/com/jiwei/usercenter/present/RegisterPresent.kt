package com.jiwei.usercenter.present

import android.app.Activity
import android.content.Context
import android.util.Log
import com.jiwei.baselibrary.present.view.BasePresent
import com.jiwei.usercenter.present.view.RegisterView
import com.jiwei.usercenter.service.UserSevice
import com.jiwei.usercenter.service.impl.UserServiceImpl

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import cn.nekocode.rxlifecycle.LifecycleEvent
import cn.nekocode.rxlifecycle.RxLifecycle
import com.jiwei.baselibrary.rx.execute
import io.reactivex.functions.Consumer


/**
 * Created by 18099 on 2018/12/17.
 */
class RegisterPresent @Inject constructor(): BasePresent<RegisterView>() {
    @Inject
    lateinit var userSevice: UserSevice
    @Inject
    lateinit var activity: Activity

    fun register(mobile: String, password: String, vertifyCode: String){
        mView.showLoading()
        userSevice.register(mobile,password,vertifyCode)
                .compose(RxLifecycle.bind(activity)
                        .disposeObservableWhen(LifecycleEvent.DESTROY_VIEW))
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.hideLoading()
                    if(it.islogin){
                        mView.onRegisterResult(true)
                    }else{
                        mView.onRegisterResult(false)
                        mView.onError()
                    }
                },{
                    mView.onError()
                    mView.hideLoading()
                    mView.onRegisterResult(false)
                })
        //使用自定义的扩展函数封装无用的操作
//        mView.showLoading()
//        userSevice.register(mobile,password,vertifyCode)
//                .execute(Consumer {
//                    if(it.islogin == true){
//                        mView.onRegisterResult(true)
//                    }else{
//                        mView.onRegisterResult(false)
//                    }
//                },activity)
    }
}