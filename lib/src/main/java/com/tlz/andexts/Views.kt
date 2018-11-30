package com.tlz.andexts

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 * Created by Tomlezen.
 * Data: 2018/10/17.
 * Time: 11:40.
 */


fun View.dp2px(dp: Float) = context.dp2px(dp)
fun View.dp2px(dp: Int) = context.dp2px(dp)

fun View.sp2px(sp: Float) = context.sp2px(sp)
fun View.sp2px(sp: Int) = context.sp2px(sp)

fun View.px2dp(px: Int) = context.px2dp(px)
fun View.px2sp(px: Int) = context.px2sp(px)

val View.densityDpi
    get() = context.densityDpi
val View.density
    get() = context.density

fun View.inflate(layoutId: Int, parent: ViewGroup?) = LayoutInflater.from(context).inflate(layoutId, parent, false)!!

fun View.drawable(resId: Int) = context.drawableRes(resId)

val View.selectableItemBackgroundResId
    get() = context.selectableItemBackgroundResId


fun View.openSoftKeyboard(){
    with(context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager){
        if(isActive){
            showSoftInput(this@openSoftKeyboard, InputMethodManager.SHOW_FORCED)
        }
    }
}

fun View.closeSoftKeyboard() = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as
        InputMethodManager).hideSoftInputFromWindow(windowToken, 0)

val Paint.textOffsetY: Float
    get() = Math.abs(fontMetrics.top + fontMetrics.bottom) / 2