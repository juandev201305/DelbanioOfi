package com.example.myapplication.data.models

data class Nivel(
    val id: Int,
    val nivel: Int,
    val cursos: List<Curso>
)