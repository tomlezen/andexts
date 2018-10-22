package com.tlz.andexts

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Tomlezen.
 * Data: 2018/10/17.
 * Time: 11:35.
 */

fun Fragment.toast(@StringRes message: Int) = activity?.toast(message)

fun Context.toast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: CharSequence) = activity?.toast(message)

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.longToast(@StringRes message: Int) = activity?.longToast(message)

fun Context.longToast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Fragment.longToast(message: CharSequence) = activity?.longToast(message)

fun Context.longToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()