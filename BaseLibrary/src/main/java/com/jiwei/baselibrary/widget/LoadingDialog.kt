package com.jiwei.baselibrary.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.jiwei.baselibrary.R
import org.jetbrains.anko.find

/**
 * Created by 18099 on 2018/12/20.
 */
class LoadingDialog private constructor(context:Context,themeResId: Int): Dialog(context,themeResId) {
    companion object {
        private lateinit var mDialog: LoadingDialog
        private lateinit var animDrawable:AnimationDrawable

        fun create(context: Context): LoadingDialog{
            mDialog = LoadingDialog(context, R.style.LightDialog)
            mDialog.setContentView(R.layout.layout_progress_dialog)
            mDialog.setCancelable(false)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            //设置窗体的明暗程度
            val layoutParams = mDialog.window.attributes
            layoutParams.dimAmount = 0.2f
            mDialog.window.attributes = layoutParams
            //获取动画
            val loadingView = mDialog.find<ImageView>(R.id.img_loading)
            animDrawable = loadingView.background as AnimationDrawable
            return mDialog
        }
    }

    fun showLoading(){
        super.show()
        animDrawable?.start()
    }

    fun hideLoading(){
        super.dismiss()
        animDrawable.stop()
    }
}