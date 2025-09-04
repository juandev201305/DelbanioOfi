package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.models.Letra
import com.example.myapplication.data.models.Nivel
import com.example.myapplication.data.repository.CursoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CursoViewModel(
    private val repository: CursoRepository = CursoRepository()
) : ViewModel() {

    private val _cursos = MutableStateFlow<List<Curso>>(emptyList())
    val cursos: StateFlow<List<Curso>> = _cursos

    init {
        listarCursos()
    }

    private fun listarCursos() {
        viewModelScope.launch {
            try {
                _cursos.value = repository.listar() // GET /curso
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun obtenerNiveles(): List<Nivel> =
        _cursos.value.map { it.nivel }.distinctBy { it.id }

    fun obtenerLetrasPorNivel(nivelId: Int): List<Letra> =
        _cursos.value.filter { it.nivel.id == nivelId }.map { it.letra }.distinctBy { it.id }

    fun obtenerCurso(nivelId: Int, letraId: Int): Curso? =
        _cursos.value.find { it.nivel.id == nivelId && it.letra.id == letraId }
}
