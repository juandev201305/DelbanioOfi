package com.example.myapplication.data.repository

import com.example.myapplication.data.models.MensajeSaliDiario
import com.example.myapplication.data.network.LiceoApi

class MensajeSaliDiarioRepository(private val api: LiceoApi) {
    suspend fun listar(): List<MensajeSaliDiario> = api.getMensSalientesDiarios()
}