package com.jiwei.baselibrary.rx

import android.app.Activity
import android.util.Log
import cn.nekocode.rxlifecycle.LifecycleEvent
import cn.nekocode.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * 使用kotlin的扩展：类名.方法名
 * Created by 18099 on 2018/12/17.
 */
fun <T> Observable<T>.execute(onNext: Consumer<T>, activity: Activity){
    this.retry(3)
            .subscribeOn(Schedulers.io())
            .compose(RxLifecycle.bind(activity).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNext, Consumer {
                Log.e("Exception",""+it.message)
            });
}