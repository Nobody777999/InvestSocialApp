package com.hfad.investsocialapp.gate

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.hfad.investsocialapp.data.Profile
import com.hfad.investsocialapp.screen.login.LoginViewModel
import com.hfad.investsocialapp.screen.profile.ProfileViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class Gate {
    private val format = Json {
        ignoreUnknownKeys = true
    }
    // объявление адреса и клиента
    private val url = "http://161.35.221.241"
    private val okHttpClient = OkHttpClient()
    private val mediaType = "application/json".toMediaType()
    // функция авторизации по логину и паролю
    fun authorize(user: String, pass: String): LoginViewModel.State{
        val body = format.encodeToString(mapOf("username" to user, "password" to pass)).toRequestBody(mediaType)

//        return LoginViewModel.State.Done


        val request = Request.Builder()
            .url("$url/login?username=$user&password=$pass")
//            .post(body)
            .build()

        val response = okHttpClient.newCall(request).execute()

        return if(response.isSuccessful){
            if (response.body != null){
                val r = format.decodeFromString<HashMap<String, String>>(response.body!!.string())
                if (r["status"] == "ok"){
                    LoginViewModel.State.Done(r["id"].toString())
                }else{
                    LoginViewModel.State.Error("Пользователь не существует")
                }
            }else{
                LoginViewModel.State.Error("Ошибка связи")
            }

        }else{
            LoginViewModel.State.Error("Ошибка связи")
        }
    }
    // функция создания нового пользователя по логину и паролю
    fun createNewUser(user: String, pass: String): LoginViewModel.State{
        val body = format.encodeToString(mapOf("username" to user, "password" to pass)).toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val response = okHttpClient.newCall(request).execute()

        return if (response.isSuccessful){
            val r = format.decodeFromString<HashMap<String, Any>>(response.body!!.string())
            if (r["status"] == "ok"){
                LoginViewModel.State.Done(r["id"].toString() )
            }else{
                LoginViewModel.State.Error("Пользователь не существует")
            }
        }else{
            LoginViewModel.State.Error("Ошибка соединения")
        }

    }



    fun getProfileById(id: String): ProfileViewModel.State{

//        val body = format.encodeToString(mapOf("id" to id)).toRequestBody(mediaType)

        val request = Request.Builder()
            .url("$url/profile?id=$id")
//            .post(body)
            .build()

        val response = okHttpClient.newCall(request).execute()

        return if (response.isSuccessful){
            if (response.body != null){
                try{
                    val r = format.decodeFromString<Profile>(response.body!!.string())
                    ProfileViewModel.State.Data(r)
                }catch (e: Exception){
                    ProfileViewModel.State.Error(e.localizedMessage)
                }
            }else{
                ProfileViewModel.State.Error("Ошибка связи")
            }
        }else{
            ProfileViewModel.State.Error("Ошибка соединения")
        }


    }

    fun uploadPhoto(uri: Uri, context: Context){
        return
//        Будет загрузка файлов
        val pathFromUri = URIPathHelper().getPath(context, uri)
        val file = File(pathFromUri!!)
        val ext = file.extension
        val type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext)
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file", file.name, file.asRequestBody(type!!.toMediaType()))
            .build()

        val request = Request.Builder().url("$url/upload").post(requestBody).build()

        val response = okHttpClient.newCall(request).execute()


        if (response.isSuccessful){
            print("Уря")
        }else{
            print("Ну вот")
        }


    }










    companion object{
        var gate: Gate? = null
        fun getInstance(): Gate{
            if(gate == null){
                gate = Gate()
            }
            return gate!!
        }
    }


}


class URIPathHelper {

    fun getPath(context: Context, uri: Uri): String? {
        val isKitKatorAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKatorAbove && DocumentsContract.isDocumentUri(context, uri)) {
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
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = uri?.let { context.contentResolver.query(it, projection, selection, selectionArgs,null) }
            if (cursor != null && cursor.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            if (cursor != null) cursor.close()
        }
        return null
    }

    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }
}


