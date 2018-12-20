package com.jiwei.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Activty管理器
 * Created by 18099 on 2018/12/20.
 */
class AppManager private constructor() {
    private val activityStack = Stack<Activity>()
    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    /**
     * 添加Activity
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /**
     * 删除Activity
     */
    fun destoryActivity(activity: Activity){
        activityStack.remove(activity)
    }

    /**
     * 获取当前Activity
     */
    fun currentActivity(){
        activityStack.lastElement()
    }

    /**
     * 删除所有Activity
     */
    fun finishActivity(){
        for(activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /**
     * 清楚后台程序退出app
     * 退出应用程序
     */
    fun exitApp(context: Context){
        finishActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }

}