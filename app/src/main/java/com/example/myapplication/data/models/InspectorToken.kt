package com.example.myapplication.data.models


data class InspectorToken(
    val id: Int? = null,
    val idInspector: Int,
    val token: String,
    val activo: Boolean
)