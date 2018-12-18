package com.jiwei.usercenter.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.jiwei.baselibrary.ui.activity.BaseMvpActivity
import com.jiwei.usercenter.R
import com.jiwei.usercenter.present.RegisterPresent
import com.jiwei.usercenter.present.view.RegisterView
import kotlinx.android.synthetic.main.activity_regist.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresent>(),RegisterView {
    override fun onRegisterResult(result: Boolean) {
        if(result == true)
            toast("登陆成功")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        mRegistBtn.setOnClickListener{
            mPresent.register(mUserNname.text.toString(),mPassWord.text.toString(),
                    mPasswordCheck.text.toString())
        }
        mPresent = RegisterPresent()
        mPresent.mView = this
    }
}
