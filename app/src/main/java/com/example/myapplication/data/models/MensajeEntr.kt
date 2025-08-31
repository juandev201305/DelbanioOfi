package com.example.myapplication.data.models

import java.time.LocalDateTime

data class MensajeEntr(
    val id: Int? = null, // lo genera el backend, puede ser null al crear
    val profesor: Profesor,
    val alumno: Alumno,
    val tipoPermiso: TipoPermiso,
    val ubicacion: Ubicacion,
    val fechaHora: String // ahora String en vez de LocalDateTime
)
