package com.example.myapplication.data.models

import java.time.LocalDateTime

data class MensajeSali(

    val id: Int,
    val nombre:String, //nombre alumno
    val curso: String, //nombre curso
    val profesor: String, // nombre profesor
    val horaSalida: LocalDateTime,
    val ubicacion: String
)
