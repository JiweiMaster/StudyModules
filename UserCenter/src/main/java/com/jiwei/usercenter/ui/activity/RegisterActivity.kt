package com.jiwei.usercenter.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.jiwei.baselibrary.common.AppManager
import com.jiwei.baselibrary.inject.component.ActivityComponent

import com.jiwei.baselibrary.ui.activity.BaseMvpActivity
import com.jiwei.usercenter.R
import com.jiwei.usercenter.injection.component.DaggerUserComponent
import com.jiwei.usercenter.injection.module.UserModule
import com.jiwei.usercenter.present.RegisterPresent
import com.jiwei.usercenter.present.view.RegisterView
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_regist.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class RegisterActivity : BaseMvpActivity<RegisterPresent>(),RegisterView {
    var lastPressTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        //初始化注入
        initInjection()

        mRegistBtn.setOnClickListener{
            mPresent.register(mUserNname.text.toString(),mPassWord.text.toString(),
                    mPasswordCheck.text.toString())
        }
    }

    private fun initInjection() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresent.mView = this

    }

    override fun onRegisterResult(result: Boolean) {
        if(result == true){
            //登陆成功
            toast("success")
        }else{
            //登陆失败
            toast("failed")
        }
    }

    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if(time-lastPressTime > 2000){
            toast("再按一次退出应用程序")
            lastPressTime = time
        }else{
            AppManager.instance.exitApp(this)
        }
    }
}
