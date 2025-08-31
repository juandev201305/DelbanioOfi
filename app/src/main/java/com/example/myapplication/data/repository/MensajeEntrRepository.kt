package com.example.myapplication.data.repository

import com.example.myapplication.data.mappers.buildMensajeEntrRequest
import com.example.myapplication.data.request.MensajeEntrRequest

import com.example.myapplication.data.models.Alumno
import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.models.MensajeEntr
import com.example.myapplication.data.models.TipoPermiso
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi
import com.example.myapplication.data.request.IdOnly
import retrofit2.Response

class MensajeEntrRepository(private val api: LiceoApi) {

    suspend fun enviarMensajeEntr(
        alumno: Alumno,
        curso: Curso,
        permiso: TipoPermiso,
        profesorId: Int,
        ubicacionId: Int,
        hora: String
    ): Response<Unit> {
        val request = buildMensajeEntrRequest(
            alumno = alumno,
            curso = curso,
            permiso = permiso,
            profesorId = profesorId,
            ubicacionId = ubicacionId,
            hora = hora
        )
        return api.crearMensajeEntr(request)
    }
}