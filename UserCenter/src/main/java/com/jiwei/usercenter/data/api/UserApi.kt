package com.jiwei.usercenter.data.api

import com.jiwei.baselibrary.data.procotol.BaseResponse
import com.jiwei.usercenter.data.procotol.LoginReq
import com.jiwei.usercenter.data.procotol.LoginRes
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by 18099 on 2018/12/18.
 */
interface UserApi {
    @GET("/salesorder/salesorder.asmx/AuthUserWithType")
    fun login(@Query("username")username: String,
              @Query("password")password: String,
              @Query("type")type: String): Observable<ResponseBody>
}