package com.bhavya.foodorder.screens

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import java.io.File
import java.io.FileOutputStream

fun getRealPathFromUri(uri: Uri, context: Context): String {
    var result = ""
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    if (cursor != null && cursor.moveToFirst()) {
        val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
        if (index != -1) {
            result = cursor.getString(index)
        } else {
            // Fallback for newer APIs
            val documentId = DocumentsContract.getDocumentId(uri)
            val split = documentId.split(":")
            val type = split[0]
            val contentUri = when (type) {
                "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                else -> null
            }
            val selection = "_id=?"
            val selectionArgs = arrayOf(split[1])
            val cursor2 = contentUri?.let {
                context.contentResolver.query(it, null, selection, selectionArgs, null)
            }
            cursor2?.use {
                if (it.moveToFirst()) {
                    result = it.getString(it.run { it.getColumnIndex(MediaStore.Images.Media.DATA) })
                }
            }
        }
        cursor.close()
    }

    if (result.isEmpty()) {
        // Fallback for file path
        val file = File(context.cacheDir, "temp.jpg")
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        outputStream.close()
        inputStream?.close()
        result = file.absolutePath
    }

    return result
}
