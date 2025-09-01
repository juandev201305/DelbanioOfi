package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.models.MensajeSali
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class MensajeSaliRepository(
    private val api: LiceoApi = ApiClient.api
) {
    suspend fun fetchUltimoMensaje(): MensajeSali? {
        return try {
            val response = api.getUltimo()

            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    Log.w("API", "⚠️ Respuesta vacía en /mensajeSali/ultimo")
                    null
                } else {
                    body
                }
            } else {
                Log.e("API", "❌ Error ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API", "❌ Excepción ${e.message}")
            null
        }
    }
}