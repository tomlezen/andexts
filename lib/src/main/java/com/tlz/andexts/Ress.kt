package com.tlz.andexts

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View

/**
 * Created by Tomlezen.
 * Data: 2018/10/16.
 * Time: 17:12.
 */

fun Context.dimen(resId: Int) = resources.getDimensionPixelSize(resId)
fun Fragment.dimen(resId: Int) = context?.dimen(resId) ?: 0
fun View.dimen(resId: Int) = context.dimen(resId)

fun Context.color(resId: Int) = ContextCompat.getColor(this, resId)
fun Fragment.color(resId: Int) = context?.color(resId) ?: 0
fun View.color(resId: Int) = context.color(resId)

fun Context.drawableRes(resId: Int) = ContextCompat.getDrawable(this, resId)
fun Fragment.drawableRes(resId: Int) = context?.drawableRes(resId)
fun View.drawableRes(resId: Int) = context.drawableRes(resId)