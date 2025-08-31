package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.models.TipoPermiso
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class TipoPermisoRepository (private val api: LiceoApi = ApiClient.api){
    suspend fun listar(): List<TipoPermiso> = api.getPermisos()
}