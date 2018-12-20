package com.jiwei.baselibrary.data.net

import android.util.TimeUtils
import com.jiwei.baselibrary.common.BaseContant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

/**
 * Created by 18099 on 2018/12/17.
 */
class RetrofitFactory private constructor() {
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }
    private val retrofit: Retrofit
    private val headerInterceptor: Interceptor

    init{
        headerInterceptor = Interceptor {
            chain ->
                val request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type","application/json")
                        .addHeader("charset","utf-8")
                        .build()
                chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(BaseContant.LOGIN_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    private fun initClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .hostnameVerifier(HostnameVerifier { _, _ ->  true})
                .build()
        return client
    }

    private fun initLogInterceptor(): Interceptor? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T{
        return retrofit.create(service)
    }

    fun modifyBaseUrl(url: String):Retrofit{
        //修改这一点的
        return retrofit.newBuilder().baseUrl(url).build()
    }
}