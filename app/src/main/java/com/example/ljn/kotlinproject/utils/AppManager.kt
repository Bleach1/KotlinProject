package com.example.ljn.kotlinproject.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.ljn.kotlinproject.App
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object AppManager {

    private val connectivityManager = App.instance.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    /**
     * 毫秒值转换 HH:mm:ss
     */
    fun timeTransformation(time: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
        return dateFormat.format(time - TimeZone.getDefault().rawOffset)
    }

    /**
     * 获取现在时间

     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    fun getNowTime(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        return formatter.format(currentTime)
    }


    /**
     * 判断是否连网
     */
    val isNetworkConnected: Boolean
        @SuppressLint("MissingPermission")
        get() {
            return connectivityManager.activeNetworkInfo.isConnected
        }

    /**
     * 判断是否WIFI处于连接状态
     */
    val isWiFiConnected: Boolean
        @SuppressLint("MissingPermission")
        get() {
            val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return networkInfo.isConnected
        }
    /**
     * 判断是否APN列表中某个渠道处于连接状态
     */
    val isMobile: Boolean
        @SuppressLint("MissingPermission")
        get() {
            val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            return networkInfo.isConnected
        }

    /**
     * 打开网络配置
     */
    fun openNetWorkSetting() {
        App.instance.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    /**
     * 获取版本号

     * @return 当前应用的版本号
     */
    fun getVersionCode(): Int {
        return App.context.packageManager?.getPackageInfo(
                App.context.packageName, 0)?.versionCode ?: -1
    }

    /**
     * 获取版本名

     * @param context
     * *
     * @return
     */
    fun getVerName(): String {
        return App.context.packageManager?.getPackageInfo(
                App.context.packageName, 0)?.versionName ?: ""
    }

    /**
     * 验证手机号

     * @param mobiles
     * *
     * @return
     */
    fun isMobileNO(mobile: String): Boolean {
        val p = Pattern.compile("^((1[0-9][0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$")
        val m = p.matcher(mobile)
        return m.matches()
    }

    /**
     * 验证邮箱

     * @param email
     * *
     * @return
     */
    fun isEmail(email: String): Boolean {
        val p = Pattern
                .compile("[\\\\w!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[\\\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\\\w](?:[\\\\w-]*[\\\\w])?\\\\.)+[\\\\w]")
        val m = p.matcher(email)
        return m.matches()
    }

    /**
     * 解决获取相册图片时候bug
     */
    fun getPhotoPath(context: Context, uri: Uri?): String {
        var filePath = ""
        val scheme = uri?.scheme
        if (TextUtils.equals("content", scheme)) {// android 4.4以上版本处理方式
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
                val wholeID = DocumentsContract.getDocumentId(uri)
                val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                val column = arrayOf(MediaStore.Images.Media.DATA)
                val sel = MediaStore.Images.Media._ID + "=?"
                val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, arrayOf(id), null)
                if (cursor != null && cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndex(column[0])
                    filePath = cursor.getString(columnIndex)
                    cursor.close()
                }
            } else {// android 4.4以下版本处理方式
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = context.contentResolver.query(uri, filePathColumn, null, null, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    filePath = cursor.getString(columnIndex)
                    Log.d("ljn", "filePath$filePath")
                    cursor.close()
                }
            }
        } else if (TextUtils.equals("file", scheme)) {// 小米云相册处理方式
            filePath = uri?.path.toString()
        }
        return filePath
    }

    /**
     * 获取url的Host
     */
    fun getHostName(urlStr: String): String {
        var urlString = urlStr
        var head = ""
        var index = urlString.indexOf("://")
        if (index != -1) {
            head = urlString.substring(0, index + 3)
            urlString = urlString.substring(index + 3)
        }
        index = urlString.indexOf("/")
        if (index != -1) {
            urlString = urlString.substring(0, index + 1)
        }
        return head + urlString
    }

    /**
     * 获取大小
     */
    fun getDataSize(var0: Long): String {
        val var2 = DecimalFormat("###.00")
        return when {
            var0 < 1024L -> var0.toString() + "bytes"
            var0 < 1048576L -> var2.format((var0.toFloat() / 1024.0f).toDouble()) + "KB"
            var0 < 1073741824L -> var2.format((var0.toFloat() / 1024.0f / 1024.0f).toDouble()) + "MB"
            var0 < 0L -> var2.format((var0.toFloat() / 1024.0f / 1024.0f / 1024.0f).toDouble()) + "GB"
            else -> "error"
        }
    }

    /**
     * 获得屏幕宽
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = Point()
        wm.defaultDisplay.getSize(p)
        return p.x
    }

    /**
     * 获得屏幕高
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = Point()
        wm.defaultDisplay.getSize(p)
        return p.y
    }

    /**
     * dip to px
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px to dip
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * Bitmap to Byte
     */
    fun bmpToByteArray(bmp: Bitmap?): ByteArray? {
        bmp ?: let { return null }
        val output = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 80, output)
        val result = output.toByteArray()
        try {
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * drawable to Bitmap
     */
    fun drawable2Bitmap(drawable: Drawable): Bitmap? {
        when (drawable) {
            is BitmapDrawable -> return drawable.bitmap
            is NinePatchDrawable -> {
                val bitmap = Bitmap
                        .createBitmap(
                                drawable.getIntrinsicWidth(),
                                drawable.getIntrinsicHeight(),
                                if (drawable.getOpacity() != PixelFormat.OPAQUE)
                                    Bitmap.Config.ARGB_8888
                                else
                                    Bitmap.Config.RGB_565)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight())
                drawable.draw(canvas)
                return bitmap
            }
            else -> return null
        }
    }

    /**
     * 根据view来生成bitmap图片，可用于截图功能
     */
    fun getViewBitmap(v: View): Bitmap? {

        v.clearFocus()
        v.isPressed = false
        val willNotCache = v.willNotCacheDrawing()
        v.setWillNotCacheDrawing(false)
        val color = v.drawingCacheBackgroundColor
        v.drawingCacheBackgroundColor = 0
        if (color != 0) {
            v.destroyDrawingCache()
        }
        v.buildDrawingCache()
        val cacheBitmap = v.drawingCache ?: return null
        val bitmap = Bitmap.createBitmap(cacheBitmap)
        v.destroyDrawingCache()
        v.setWillNotCacheDrawing(willNotCache)
        v.drawingCacheBackgroundColor = color
        return bitmap

    }

}