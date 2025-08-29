package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Alumno
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class AlumnoRepository(private val api: LiceoApi = ApiClient.api) {
    suspend fun listar(): List<Alumno> = api.getAlumnos()
}