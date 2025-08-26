package com.example.myapplication.data

import retrofit2.http.GET

interface LiceoApi {
    @GET("profesor")
    suspend fun getProfesores(): List<Profesor>
}