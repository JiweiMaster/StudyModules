package com.jiwei.baselibrary.widget

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.util.AttributeSet
import android.widget.Button
import com.jiwei.baselibrary.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*
import org.jetbrains.anko.attr

/**
 * Created by 18099 on 2018/12/21.
 */
class TimeButton @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null)
    : Button(context, attrs) {
    private var defaultText:String? = "获取验证码"
    private var timeCount:Int? = 60
    private var timer: CountDownTimer

    init{
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TimeButton)
        defaultText = typeArray.getString(R.styleable.TimeButton_defaultText)
        timeCount = typeArray.getInt(R.styleable.TimeButton_timeCount,60)
        this.text = defaultText
        typeArray.recycle()

        timer = object: CountDownTimer(((timeCount!! *1000).toLong()),1000) {
            override fun onFinish() {
                this@TimeButton.text = "重获验证码"
                this@TimeButton.setBackgroundColor(resources.getColor(R.color.transparent))
                this@TimeButton.setTextColor(resources.getColor(R.color.common_blue))
                this@TimeButton.isEnabled = true

            }
            override fun onTick(p0: Long) {
                this@TimeButton.text = (p0/1000).toString() + " s "
            }
        }

    }

    fun startTimer(){
        timer!!.start()
        this@TimeButton.isEnabled = false
        this@TimeButton.setTextColor(resources.getColor(R.color.common_white))
        this@TimeButton.setBackgroundColor(resources.getColor(R.color.common_gray))
    }

    fun stopTimer(){
        timer.onFinish()
    }
}