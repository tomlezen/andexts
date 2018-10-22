@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.tlz.andexts

import android.os.Build

inline fun doFromSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT >= version) f()
}

inline fun doIfSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT == version) f()
}