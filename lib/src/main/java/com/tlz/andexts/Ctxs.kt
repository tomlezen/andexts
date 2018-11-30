package com.tlz.andexts

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.telephony.TelephonyManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import java.io.File

/**
 * Created by Tomlezen.
 * Data: 2018/10/17.
 * Time: 11:42.
 */

val Context.appName
    get() = appName(packageName)

fun Context.appName(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.applicationInfo?.loadLabel(packageManager).toString()

val Context.appIcon
    get() = appIcon(packageName)

fun Context.appIcon(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.applicationInfo?.loadIcon(packageManager)

val Context.versionName
    get() = versionName(packageName)

fun Context.versionName(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.versionName

val Context.versionCode
    get() = versionCode(packageName)

fun Context.versionCode(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.versionCode ?: 0

fun Context.metaData(key: String) = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)?.metaData?.getString(key)

val Context.imei
    @SuppressLint("MissingPermission")
    get() = try {
        (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
    } catch (var3: Exception) {
        null
    }

fun Context.appExists(packageName: String) = packageManager.getLaunchIntentForPackage(packageName) != null

fun Context.activityExists(packageName: String, activityName: String) =
    Intent().let {
        it.setClassName(packageName, activityName)
        packageManager.resolveActivity(it, 0) != null || it.resolveActivity(packageManager) != null || packageManager.queryIntentActivities(it, 0).size > 0
    }

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

fun Context.install(apkFile: File) {
    val install = Intent(Intent.ACTION_VIEW)
    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val apkUri = FileProvider.getUriForFile(this, "$packageName.AndExtsFileProvider", apkFile)
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
    } else {
        install.setDataAndType(Uri.parse("file://" + apkFile.absolutePath), "application/vnd.android.package-archive");
    }
    startActivity(install)
}


fun Context.dp2px(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
fun Context.dp2px(dp: Int) = dp2px(dp.toFloat()).toInt()

fun Context.sp2px(sp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
fun Context.sp2px(sp: Int) = sp2px(sp.toFloat()).toInt()

fun Context.px2dp(px: Int) = px / resources.displayMetrics.density
fun Context.px2sp(px: Int) = px / resources.displayMetrics.scaledDensity

val Context.densityDpi
    get() = resources.displayMetrics.densityDpi
val Context.density
    get() = resources.displayMetrics.density

val Context.screenWidth
    get() = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width
val Context.screenHeight
    get() = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height

val Context.statusbarHieght
    get() = resources.getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"))

val Context.actionbarSize: Int
    get() {
        val ta = obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val value = ta.getDimensionPixelSize(ta.getIndex(0), 0)
        ta.recycle()
        return value
    }

val Context.selectableItemBackgroundResId
    get() = TypedValue().apply { theme.resolveAttribute(R.attr.selectableItemBackground, this, true) }.resourceId

fun Context.inflate(resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(this).inflate(resId, parent, attachToRoot)

fun Context.toggleSoftKeyboard() {
    with(getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager) {
        if (isActive) {
            toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}

@SuppressLint("MissingPermission")
fun Context.makeCall(number: String): Boolean =
    try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

fun Context.share(text: String, subject: String = ""): Boolean =
    try {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }

inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent =
    ComInternals.createIntent(this, T::class.java, params)
