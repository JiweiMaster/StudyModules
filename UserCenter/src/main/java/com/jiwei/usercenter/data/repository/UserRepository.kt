package com.jiwei.usercenter.data.repository

import android.util.Log
import com.jiwei.baselibrary.data.net.RetrofitFactory
import com.jiwei.usercenter.data.api.UserApi
import com.jiwei.usercenter.data.procotol.LoginRes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by 18099 on 2018/12/18.
 */
class UserRepository @Inject constructor() {
    fun login(userName: String, passWord: String, type: String): Observable<LoginRes>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(userName,passWord,type)
    }
}

