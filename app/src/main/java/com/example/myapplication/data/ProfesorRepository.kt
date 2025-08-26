package com.example.myapplication.data

data class ProfesorRepository(private val api: LiceoApi = ApiClient.api){
    suspend fun listar(): List<Profesor> = api.getProfesores()
}
