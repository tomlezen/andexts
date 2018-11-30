@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.tlz.andexts

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.ViewGroup

/**
 * Created by Tomlezen.
 * Data: 2018/10/17.
 * Time: 14:06.
 */

fun Fragment.browse(url: String, newTask: Boolean = false): Boolean = activity?.browse(url, newTask) ?: false

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
    activity?.let { ComInternals.internalStartActivity(it, T::class.java, params) }
}

inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
    activity?.let { startActivityForResult(ComInternals.createIntent(it, T::class.java, params), requestCode) }
}

inline fun <reified T : Service> Fragment.startService(vararg params: Pair<String, Any>) {
    activity?.let { ComInternals.internalStartService(it, T::class.java, params) }
}

inline fun <reified T : Any> Fragment.intentFor(): Intent = Intent(activity, T::class.java)

var Fragment.windowStatusBarColor: Int
    set(value) {
        activity?.windowStatusBarColor = value
    }
    get() = activity?.windowStatusBarColor ?: 0

fun Fragment.inflate(resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) = context?.inflate(resId, parent, attachToRoot)

inline fun Fragment.makeCall(number: String): Boolean = activity?.makeCall(number) ?: false

inline fun Fragment.share(text: String, subject: String = "") = activity?.share(text, subject) ?: false

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
    activity?.let { ComInternals.createIntent(it, T::class.java, params) } ?: Intent()