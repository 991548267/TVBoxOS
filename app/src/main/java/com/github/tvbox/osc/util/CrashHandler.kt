package com.github.tvbox.osc.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author: 99154
 * @description APK闪退异常捕获
 * @date: Create in 10:27 2022/6/21
 */
class CrashHandler private constructor(val context: Context) : Thread.UncaughtExceptionHandler {

    companion object : SingletonHolder<CrashHandler, Context>(::CrashHandler)

    private val tag: String = CrashHandler::class.simpleName.toString()

    private val defaultHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
        Log.i(tag, "CrashHandler init")
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        if (!handlerException(ex) && defaultHandler != null) {
            // 如果未处理，交给系统处理
            defaultHandler.uncaughtException(thread, ex)
        } else {
            // 延时两秒后再结束APK进程
            Thread.sleep(2000L)
            // 结束APK进程
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    private fun handlerException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }
        ex.printStackTrace()
        saveCrash2File(ex)
        return true
    }

    fun saveCrash2File(ex: Throwable) {
        val builder = StringBuilder()
        // 收集APK相关信息
        builder.append(collectAPKInfo())
        // 收集设备相关信息
        builder.append(collectDeviceInfo())
        // 收集崩溃日志信息
        builder.append(ex.message)
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        builder.append(writer.toString())
        val format = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault())
        val time = format.format(Date())
        val logName = "crash-$time.log"
        val crashDir = File(context.externalCacheDir?.absolutePath, "crash")
        val size = FileUtil.getFileDirSize(crashDir)
        Log.i(tag, "crash dir size: $size")
        if (size > 1024 * 1024L) { // 大于1M时，清除所有本地崩溃日志
            FileUtil.deleteFileDir(crashDir.absolutePath)
        }
        if (!crashDir.exists()) {
            crashDir.mkdirs()
        }
        val logPath = "${crashDir.absoluteFile}${File.separator}$logName"
        Log.i(tag, logPath)
        try {
            val fos = FileOutputStream(logPath)
            fos.write(builder.toString().toByteArray())
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 收集设备相关信息
     * @return StringBuilder
     */
    private fun collectDeviceInfo(): String {
        val builder = StringBuilder()
        // 获取所有系统信息
        val fields: Array<Field> = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                builder.append(field.name).append("--").append(field.get(Any())?.toString()).append("\n")
            } catch (e: Exception) {
                Log.e(tag, "an error occurred when collect device info", e)
                continue
            }
        }
        return builder.toString()
    }

    private fun collectAPKInfo(): String {
        val info = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
        if (info != null) {
            val builder = StringBuilder()
            builder.append("versionName").append("--").append(info.versionName).append("\n")
            builder.append("versionCode").append("--").append(info.versionCode).append("\n")
            return builder.toString()
        }
        return ""
    }
}