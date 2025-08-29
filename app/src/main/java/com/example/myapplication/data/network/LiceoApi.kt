package com.example.myapplication.data.network

import com.example.myapplication.data.models.Alumno
import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.models.Letra
import com.example.myapplication.data.models.Nivel
import com.example.myapplication.data.models.Profesor
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

    @GET("nivel")
    suspend fun getNiveles(): List<Nivel>

    @GET("curso")
    suspend fun getCursos(): List<Curso>

    @GET("letra")
    suspend fun getLetras(): List<Letra>

    @GET("alumno")
    suspend fun getAlumnos(): List<Alumno>
}