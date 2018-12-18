package com.jiwei.usercenter.service

import io.reactivex.Observable

/**
 * Created by 18099 on 2018/12/17.
 */

interface UserSevice{
    fun register(mobile: String, password: String, vertifyCode: String):Observable<Boolean>
}
