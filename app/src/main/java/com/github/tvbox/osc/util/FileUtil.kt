package com.github.tvbox.osc.util

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import java.io.*
import java.util.*


/**
 * @author wq
 * @date 2019-10-17 16:12
 * @desc FIleUtils
 */
object FileUtil {
    private val DATA_TYPE_ALL = "*/*"
    private val DATA_TYPE_APK = "application/vnd.android.package-archive"
    private val DATA_TYPE_VIDEO = "video/*"
    private val DATA_TYPE_AUDIO = "audio/*"
    private val DATA_TYPE_HTML = "text/html"
    private val DATA_TYPE_IMAGE = "image/*"
    private val DATA_TYPE_PPT = "application/vnd.ms-powerpoint"
    private val DATA_TYPE_EXCEL = "application/vnd.ms-excel"
    private val DATA_TYPE_WORD = "application/msword"
    private val DATA_TYPE_CHM = "application/x-chm"
    private val DATA_TYPE_TXT = "text/plain"
    private val DATA_TYPE_PDF = "application/pdf"

    fun getFileType(filePath: String): String {

        val file = File(filePath)
        if (!file.exists()) {

            return DATA_TYPE_ALL
        }
        /* 取得扩展名 */
        val end =
            file.name.substring(file.name.lastIndexOf(".") + 1, file.name.length)
                .toLowerCase(Locale.getDefault())

        if (end == "m4a" || end == "mp3" || end == "mid" || end == "xmf" || end == "ogg" || end == "wav") {
            return DATA_TYPE_AUDIO
        } else if (end == "3gp" || end == "mp4") {
            return DATA_TYPE_VIDEO
        } else if (end == "jpg" || end == "gif" || end == "png" || end == "jpeg" || end == "bmp") {
            return DATA_TYPE_IMAGE
        } else if (end == "apk") {
            return DATA_TYPE_APK
        } else if (end == "ppt") {
            return DATA_TYPE_PPT
        } else if (end == "xls" || end == "xlsx") {
            return DATA_TYPE_EXCEL
        } else if (end == "doc" || end == "docx") {
            return DATA_TYPE_WORD
        } else if (end == "pdf") {
            return DATA_TYPE_PDF
        } else if (end == "chm") {
            return DATA_TYPE_CHM
        } else if (end == "txt") {
            return DATA_TYPE_TXT
        } else {
            return DATA_TYPE_ALL
        }
    }

    fun getChooseFileResultPath(context: Context, uri: Uri): String? {
        try {
            if ("file".equals(uri.scheme, ignoreCase = true)) { //使用第三方应用打开
                return uri.path
            }
            return getPath(context, uri)
        } catch (e: Exception) {
            return null
        }

    }

    private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.getContentResolver().query(contentUri, proj, null, null, null)
        if (null != cursor && cursor.moveToFirst()) {
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
            cursor.close()
        }
        return res
    }

    fun getFilePath(context: Context, uri: Uri): String? {
        var cursor: Cursor? = null
        val projection = arrayOf(
            MediaStore.MediaColumns.DISPLAY_NAME
        )
        try {
            cursor = context.contentResolver.query(
                uri, projection, null, null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {

                val index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @Description:    从小文件中读数据（字符串）-（大文件请使用Jackson分段读取）
     * https://www.jianshu.com/p/6abb3dbe0c8b
     * @Params:         DATA_TYPE_ALL
     * @Return:
     * @author:         black
     * @date:           2022/9/1-16:48
     */
    fun getStringFromFile(filePath: String): String? {
        val file = File(filePath)
        if (!file.exists()) {
            return null
        }
        var inputStream: FileInputStream? = null
        var streamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        val buffer = StringBuffer()
        try {
            inputStream = FileInputStream(file) //通过字节流获取
            streamReader = InputStreamReader(inputStream)
            bufferedReader = BufferedReader(streamReader)
            buffer.append(bufferedReader.readLine())
            bufferedReader.safeClose()
            streamReader.safeClose()
            inputStream.safeClose()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } finally {
            bufferedReader?.safeClose()
            streamReader?.safeClose()
            inputStream?.safeClose()
        }
        return buffer.toString()
    }

    @SuppressLint("NewApi")
    private fun getPath(context: Context, uri: Uri): String? {
        val isKitKat: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {

                val id = DocumentsContract.getDocumentId(uri)
                val contentUriPrefixesToTry = arrayOf(
                    "content://downloads/public_downloads",
                    "content://downloads/my_downloads",
                    "content://downloads/all_downloads"
                )
                var filePath: String? = null
                contentUriPrefixesToTry.forEach {

                    try {
                        val contentUri = ContentUris.withAppendedId(
                            Uri.parse(it),
                            java.lang.Long.valueOf(id)
                        )
                        filePath = getDataColumn(context, contentUri, null, null)
                        if (!filePath.isNullOrEmpty()) {
                            return@forEach
                        }
                    } catch (e: Exception) {

                    }

                }
                val fileName: String? = getFilePath(context, uri)

                if (filePath.isNullOrEmpty() || !File(filePath).exists()) {
                    filePath = getPathFromInputStreamUri(context, uri, fileName ?: "")
                }

                return filePath


            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                when (type) {
                    "image" -> {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    }
                    "video" -> {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    }
                    "audio" -> {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                }
                val selection = "_id=?"
                val selectionArgs =
                    arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.getScheme(), ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.getScheme(), ignoreCase = true)) {
            uri.path
        }
        return null
    }

    private fun getDataColumn(
        context: Context,
        uri: Uri?,
        selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        if (uri == null) {
            return null
        }
        var cursor: Cursor? = null
        val column = MediaStore.Files.FileColumns.DATA
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    private fun getPathFromInputStreamUri(
        context: Context,
        uri: Uri,
        fileName: String
    ): String? {
        var inputStream: InputStream? = null
        var filePath: String? = null
        if (uri.authority != null) {
            try {
                inputStream = context.contentResolver.openInputStream(uri)
                val file = createTemporalFileFrom(context, inputStream, fileName)
                filePath = file!!.path
            } catch (e: java.lang.Exception) {

            } finally {
                try {
                    inputStream?.close()
                } catch (e: java.lang.Exception) {

                }
            }
        }
        return filePath
    }

    @Throws(IOException::class)
    private fun createTemporalFileFrom(
        context: Context,
        inputStream: InputStream?,
        fileName: String
    ): File? {
        var targetFile: File? = null
        if (inputStream != null) {
            var read: Int = 0
            val buffer = ByteArray(8 * 1024)

            targetFile = File(Environment.DIRECTORY_DOWNLOADS, fileName)
            if (targetFile!!.exists()) {
                targetFile.delete()
            }
            val outputStream: OutputStream = FileOutputStream(targetFile)
            while (inputStream.read(buffer).also({ read = it }) != -1) {
                outputStream.write(buffer, 0, read)
            }
            outputStream.flush()
            try {
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return targetFile
    }

    fun saveBitmap(context: Context, bitmap: Bitmap): String {

        val dir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "water"
        )

        if (!dir.isDirectory) {
            dir.mkdirs()
        }
        val file = File(
            dir,
            "${System.currentTimeMillis()}.jpg"
        )

        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out)
            out.flush()
            out.close()
            bitmap.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return file.absolutePath

    }

    fun deleteFiles(images: MutableList<String>) {
        images.forEach {
            deleteFile(it)
        }
    }

    fun deleteFile(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {

        }
    }

    fun deleteFileDir(path: String) {
        val file = File(path)
        if (file.exists()) {
            if (file.isDirectory) {
                val files = file.listFiles()
                files?.forEach {
                    if (it.isDirectory) {
                        deleteFileDir(it.absolutePath)
                        deleteFile(it.absolutePath)
                    } else {
                        deleteFile(it.absolutePath)
                    }
                }
                deleteFile(file.absolutePath)
            } else {
                deleteFile(file.absolutePath)
            }
        }
    }

    fun getFileSize(file: File): Long {
        var size = 0L
        if (!file.exists()) {
            return size
        }
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(file)
            size = inputStream.available().toLong()
        } catch (e: Exception) {
        } finally {
            inputStream?.close()
        }
        return size
    }

    fun getFileDirSize(file: File): Long {
        var size = 0L
        if (!file.exists()) {
            return size
        }
        if (file.isDirectory) {
            val files = file.listFiles()
            files?.forEach {
                size += if (it.isDirectory) {
                    getFileDirSize(it)
                } else {
                    getFileSize(it)
                }
            }
        } else {
            return getFileSize(file)
        }
        return size
    }
}

fun Closeable.safeClose() {
    try {
        close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}