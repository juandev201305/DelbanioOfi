package com.example.myapplication.data

import retrofit2.http.GET
/**
 * En esta interfaz se definen los endspoints de la api
*/
interface LiceoApi {
    //Se indica el tipo y el mapping del metodo
    @GET("profesor")
    //Esto va a pedir la lista profesor
    suspend fun getProfesores(): List<Profesor>
    //El suspend significa que se ejecuta de forma asincrona
    //(No congela la app mientras espera la respuesta)

    //fun getProfesores() es el metodo que va a pedir la lista de profesores
    // : List<Profesor> esto devuelve una lista de objetos Profesor
    //Conve
}