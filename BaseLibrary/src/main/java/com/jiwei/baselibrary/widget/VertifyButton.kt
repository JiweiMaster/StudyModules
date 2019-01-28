package com.jiwei.baselibrary.widget

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.Button
import com.jiwei.baselibrary.R

/**
 * Created by 18099 on 2018/12/20.
 */
class VertifyButton(mContext: Context, attrs: AttributeSet): Button(mContext,attrs) {
    private var timeCount = 10
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mHandler: Handler

    init{
        this.text = "获取验证码"
        mHandler = Handler()
    }

    /*
        倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        mHandler.postDelayed(countDown, 0)
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick()
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener{
        fun onItemClick()
    }

    private val countDown = object: Runnable{
        override fun run() {
            this@VertifyButton.text = timeCount.toString() + " s "
            this@VertifyButton.setBackgroundColor(resources.getColor(R.color.common_disable))
            this@VertifyButton.setTextColor(resources.getColor(R.color.common_white))
            this@VertifyButton.isEnabled = false

            if(timeCount > 0){
                mHandler.postDelayed(this,1000)
            }else{
                resetTimeCounter()
            }
            timeCount--
        }
    }

    fun removeRunable(){
        mHandler.removeCallbacks(countDown)
    }

    fun resetTimeCounter(vararg text: String){
        this.isEnabled = true
        if(text.isNotEmpty() && ""!= text[0]){
            this.text = text[0]
        }else{
            this.text = "重获验证码"
        }
        this.setBackgroundColor(resources.getColor(R.color.transparent))
        this.setTextColor(resources.getColor(R.color.common_blue))
        timeCount = 10
    }

}