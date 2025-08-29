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
 * Verción : 0.0.0.1
 * Cambios:
 *
 * Fecha : 27-08-2025
 *
 */

/**
 * Se declara un objeto Singleton llamado "ApiCliente";
 * Un objeto Singleton es una clase que tendra una sola
 * en toda la App
 */
object ApiClient {
    //Se define la url BASE de la api en una variable constante y privada.
    //luego se agregaran los endspoint
    private const val URL_BASE = "http://181.212.53.98:8080/liceo/"
    //Aqui se crea un cliente HTTP que se encarga de
    //Enviar y recibir peticiones.
    //by lazy significa que el objeto se crea solo la primera vez que se usa
    private val cliente by lazy {
        //Se declara una variable que captura las peticiones y repuestas
        //Configuramos el nivel de detalles de los logs
        //.BODY es para que muestre toda la informacion de peticion y respuestas
        val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        //Aca se contruye el cliente HTTP y se añade un interceptor para
        //ver las peticiones en el logcat
        OkHttpClient.Builder()
            .addInterceptor(log)
            .build()
    }
    //Se crea una propieda del tipo LiceoApi
    val api: LiceoApi by lazy {
        /*
         * Se construye el objeto Retrofit:
         * El cual se encarga de convertir interfaces
         * en codigo real que hace peticiones
        */
        Retrofit.Builder()
            //Se le indica la URL BASE de la api a consumir
            .baseUrl(URL_BASE)
            //Se le indica a RetroFit que use el
            //el OkHttpCliente que se construyó en cliente
            .client(cliente)
            //Se le agrega un convertidor de JSON
            //esto para que el json recibido se convierta en objeto Kotlin y viceversa
            .addConverterFactory(GsonConverterFactory.create())
            //Se construye finalmente el objeto RetroFit con .build()
            .build()
            //Esto le indica a RetroFit que tome la interfaz LiceoApi y cree una
            //implementacion automatica para que pueda usarlo como un objeto real
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