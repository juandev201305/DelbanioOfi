package com.example.myapplication.data.network

import com.example.myapplication.data.network.LiceoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * CONEXION A LA API REST FULL
 *
 * Autor : Juan Correa
 * Revision : Mauricio Saavedra
 * Versión : 0.0.0.1
 * Cambios:
 *
 * Fecha : 27-08-2025
 *
 */

/**
 * Se declara un objeto Singleton llamado "ApiCliente";
 * Un objeto Singleton es una clase que tendrá una sola
 * instancia en toda la App.
 */
object ApiClient {
    private const val URL_BASE = "http://181.212.53.98:9090/liceo/"

    private val cliente by lazy {
        val log = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(log)
            .build()
    }

    val api: LiceoApi by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create()) // ya no hace falta Gson custom
            .build()
            .create(LiceoApi::class.java)
    }
}


/**
 * DEFINICIONES
 * .Builder() → Inicia la construcción de un objeto (Retrofit u OkHttp).
 * .baseUrl() → Define la URL base de la API.
 * .client() → Asocia un cliente HTTP (OkHttp) a Retrofit.
 * .addInterceptor() → Añade un interceptor (ej: logs de peticiones/respuestas).
 * .addConverterFactory() → Configura cómo convertir datos JSON ↔ objetos Kotlin.
 * .apply{} → Aplica configuraciones rápidas a un objeto recién creado.
 * .build() → Finaliza la construcción y devuelve el objeto listo para usar.
 * .create(Interfaz::class.java) → Genera una implementación real de la interfaz de la API.
 * by lazy → Inicializa la variable solo cuando se usa por primera vez (lazy loading).
 * const val → Declara una constante inmutable (valor fijo en tiempo de compilación).
 */