package com.jiwei.usercenter.service

import com.jiwei.usercenter.data.procotol.LoginRes
import io.reactivex.Observable

/**
 * Created by 18099 on 2018/12/17.
 */

interface UserSevice{
    fun register(mobile: String, password: String, vertifyCode: String): Observable<LoginRes>
}
