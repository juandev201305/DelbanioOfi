package com.example.myapplication.data.mappers

import com.example.myapplication.data.models.Alumno
import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.request.MensajeEntrRequest
import com.example.myapplication.data.models.MensajeEntr
import com.example.myapplication.data.models.TipoPermiso
import com.example.myapplication.data.request.IdOnly

// Extensiones para convertir a IdOnly
fun Alumno.toIdOnly(): IdOnly = IdOnly(this.id)
fun Curso.toIdOnly(): IdOnly = IdOnly(this.id)
fun TipoPermiso.toIdOnly(): IdOnly = IdOnly(this.id)

// Construcción del request listo para enviar
fun buildMensajeEntrRequest(
    alumno: Alumno,
    curso: Curso,
    permiso: TipoPermiso,
    profesorId: Int,
    inspectorId: Int?,   // 👈 lo puedes mantener, pero ya no se usará
    ubicacionId: Int,
    hora: String
): MensajeEntrRequest {
    return MensajeEntrRequest(
        alumno = IdOnly(alumno.id),
        curso = IdOnly(curso.id),
        permiso = IdOnly(permiso.id),
        profesor = profesorId,
        inspector = 1,   // 👈 aquí siempre fijo en 1
        ubicacion = ubicacionId,
        horaEntrante = hora
    )
}