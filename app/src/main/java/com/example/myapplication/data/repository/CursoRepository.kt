package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class CursoRepository(private val api: LiceoApi = ApiClient.api) {
    suspend fun listar(): List<Curso> = api.getCursos()
}