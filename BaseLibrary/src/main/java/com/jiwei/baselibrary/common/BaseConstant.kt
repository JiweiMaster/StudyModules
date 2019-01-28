package com.jiwei.baselibrary.common

/**
 * Created by 18099 on 2018/12/18.
 */
class BaseConstant {
    companion object {
        val LOGIN_ADDRESS = "http://218.94.37.243:8081"
        //使用包名作为标记
        val TABLE_PREFS = BaseApplication.context.packageName.toString()
    }
}