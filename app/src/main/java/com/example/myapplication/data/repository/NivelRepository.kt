package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Nivel
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class NivelRepository(private val api: LiceoApi = ApiClient.api) {
    suspend fun listar(): List<Nivel> = api.getNiveles()
}
