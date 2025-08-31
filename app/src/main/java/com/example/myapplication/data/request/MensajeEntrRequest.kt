package com.example.myapplication.data.request

import com.example.myapplication.data.models.Alumno
import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.models.TipoPermiso
data class MensajeEntrRequest(
    val alumno: IdOnly,
    val curso: IdOnly,
    val permiso: IdOnly,
    val profesor: Int,
    val inspector: Int,
    val ubicacion: Int,
    val horaEntrante: String
)

data class IdOnly(val id: Int)