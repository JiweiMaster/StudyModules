package com.jiwei.baselibrary.ext

import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.*


/**
 * Created by 18099 on 2018/12/18.
 */
class GsonUtil {
    companion object {
        private val gson = Gson()
        fun ObjectToJsonStr(src: Any): String{
            return gson.toJson(src)
        }

        fun <T> JsonStrToObject(jsonStr: String, typeOfT: Type): T {
            return gson.fromJson(jsonStr,typeOfT)
        }
    }
}