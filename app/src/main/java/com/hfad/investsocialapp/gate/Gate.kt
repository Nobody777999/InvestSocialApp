package com.hfad.investsocialapp.gate

import com.hfad.investsocialapp.screen.login.LoginViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class Gate {
    val format = Json {
        ignoreUnknownKeys = true
    }
        // объявление адреса и клиента
    val url = "http://161.35.221.241"
    val okHttpClient = OkHttpClient()
    val mediaType = "application/json".toMediaType()
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
                    LoginViewModel.State.Done
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
        val body = format.encodeToString(mapOf("user" to user, "pass" to pass)).toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val response = okHttpClient.newCall(request).execute()

        return if (response.isSuccessful){
            LoginViewModel.State.Done
        }else{
            LoginViewModel.State.Error("Ошибка соединения")
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


