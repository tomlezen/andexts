@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.tlz.andexts

import android.app.Activity
import android.app.Service
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

/**
 * Created by Tomlezen.
 * Data: 2018/10/17.
 * Time: 13:54.
 */

var Activity.windowStatusBarColor: Int
    get() = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor
        } else {
            0
        }
    } catch (e: Exception) {
        0
    }
    set(value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = value
            }
        } catch (e: Exception) {
        }
    }


fun Activity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
    }
}

inline fun <reified T : Activity> Activity.startActivity(vararg params: Pair<String, Any>) {
    ComInternals.internalStartActivity(this, T::class.java, params)
}

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
    ComInternals.internalStartActivityForResult(this, T::class.java, requestCode, params)
}

inline fun <reified T : Service> Activity.startService(vararg params: Pair<String, Any>) {
    ComInternals.internalStartService(this, T::class.java, params)
}
