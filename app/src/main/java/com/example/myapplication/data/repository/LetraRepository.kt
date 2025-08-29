package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Letra
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class LetraRepository(private val api: LiceoApi = ApiClient.api) {
    suspend fun listar(): List<Letra> = api.getLetras()
}