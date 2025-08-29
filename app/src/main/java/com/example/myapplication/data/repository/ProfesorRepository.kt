package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Profesor
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

/**
 * Es la capa intermedia entre la app y la api.
 * El repositorio se encarga de pedir y devolver datos
*/


//Se define la clase repository y de parametro recibe
//api: LiceoApi (por defecto sa ApiCliente.api)
data class ProfesorRepository(private val api: LiceoApi = ApiClient.api){
    //Se define la funcion listar usando suspend fun
    //Esta funcion llama al metodo getProfesores() de la interfaz LiceoApi
    //y nos devuelve una lista de profesores
    suspend fun listar(): List<Profesor> = api.getProfesores()
}