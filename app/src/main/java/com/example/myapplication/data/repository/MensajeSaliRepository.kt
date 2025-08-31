package com.example.myapplication.data.repository

import com.example.myapplication.data.models.MensajeSali
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi


data class MensajeSaliRepository(
    private val api: LiceoApi = ApiClient.api
) {
    // Trae el último mensaje (y el endpoint ya lo borra de la DB en el backend)
    suspend fun getUltimoMensaje(): MensajeSali = api.getUltimo()
}