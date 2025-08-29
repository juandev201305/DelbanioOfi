package com.example.myapplication.data.models

data class Curso(
    val id: Int,
    val letra: Letra,
    val alumnos: List<Alumno>
)