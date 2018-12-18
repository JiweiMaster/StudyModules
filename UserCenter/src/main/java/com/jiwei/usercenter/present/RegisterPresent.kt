package com.jiwei.usercenter.present

import com.jiwei.baselibrary.present.view.BasePresent
import com.jiwei.baselibrary.rx.execute
import com.jiwei.usercenter.present.view.RegisterView
import com.jiwei.usercenter.service.impl.UserServiceImpl
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

/**
 * Created by 18099 on 2018/12/17.
 */
class RegisterPresent: BasePresent<RegisterView>() {

    fun register(mobile: String, password: String, vertifyCode: String){
        /**
         * 处理业务逻辑
         */
        val userSevice = UserServiceImpl()
        userSevice.register(mobile,password,vertifyCode)
                .execute(Consumer<Boolean>{
                    mView.onRegisterResult(true)
                }, Consumer<Throwable>{

                })


    }
}