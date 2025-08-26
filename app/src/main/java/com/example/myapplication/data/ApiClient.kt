package com.example.myapplication.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val url_base = "http://181.212.53.98:8080/liceo/"

    private val cliente by lazy {
        val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder()
            .addInterceptor(log)
            .build()
    }

    val api: LiceoApi by lazy {
        Retrofit.Builder()
            .baseUrl(url_base)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LiceoApi::class.java)
    }
}