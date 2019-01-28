package com.jiwei.baselibrary.rx

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import cn.nekocode.rxlifecycle.LifecycleEvent
import cn.nekocode.rxlifecycle.RxLifecycle
import com.jiwei.baselibrary.R
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
            })
}

/**
 * 根据传入方法的值，判断按钮是否可以显示出来
 */
fun Button.enable(et:EditText, method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btn.isEnabled = method()
            if(btn.isEnabled){
                btn.setBackgroundColor(resources.getColor(R.color.common_blue))
            }else{
                btn.setBackgroundColor(resources.getColor(R.color.common_disable))

            }
        }

    })

}