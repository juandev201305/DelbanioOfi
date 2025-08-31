package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Ubicacion
import com.example.myapplication.data.network.ApiClient

class UbicacionRepository {
    private val api = ApiClient.api

    // Obtener todas las ubicaciones
    suspend fun listarUbicaciones(): List<Ubicacion> {
        return api.getUbicaciones()
    }
}

