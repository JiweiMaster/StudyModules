package com.jiwei.usercenter.ui.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.inputmethod.InputMethodManager
import com.jiwei.baselibrary.common.AppManager
import com.jiwei.baselibrary.inject.component.ActivityComponent
import com.jiwei.baselibrary.rx.enable

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
        initView()


    }

    fun isLoginBtnEnable():Boolean{
        return mUserNname.text.isEmpty().not() &&
                mPassWord.text.isEmpty().not() &&
                mPasswordCheck.text.isEmpty().not()
    }


    /**
     * 初始化对应的控件
     */
    private fun initView() {
        //监听editext为空的时候不允许按键按下
        mRegistBtn.enable(mUserNname,{isLoginBtnEnable()})
        mRegistBtn.enable(mPassWord,{isLoginBtnEnable()})
        mRegistBtn.enable(mPasswordCheck,{isLoginBtnEnable()})

        mRegistBtn.setOnClickListener{
            mPresent.register(mUserNname.text.toString(),mPassWord.text.toString(),
                    mPasswordCheck.text.toString())
        }

        vertifyCodeBtn.setOnClickListener{
            vertifyCodeBtn.requestSendVerifyNumber()
        }

        vertifyCodeBtn2.setOnClickListener{
            vertifyCodeBtn2.startTimer()
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
