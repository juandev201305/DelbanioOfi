package com.example.myapplication.data.models

data class MensajeSaliDiario(
    val id: Int,
    val permiso: String,
    val alumno:String, //nombre alumno
    val curso: String, //nombre curso
    val profesor: String, // nombre profesor
    val horaSalida: String,
    val ubicacion: String
)
