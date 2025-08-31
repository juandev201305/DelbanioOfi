package com.example.myapplication.data.models

data class Curso(
    val id: Int,
    val letra: Letra,
    val nivel: Nivel,
    val alumnos: List<Alumno>
)