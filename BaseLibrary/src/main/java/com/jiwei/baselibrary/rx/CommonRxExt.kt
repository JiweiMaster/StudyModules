package com.jiwei.baselibrary.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * 使用kotlin的扩展：类名.方法名
 * Created by 18099 on 2018/12/17.
 */
fun <T> Observable<T>.execute(onNext: Consumer<T>, onError: Consumer<Throwable> ){
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNext,onError);
}