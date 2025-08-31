package com.example.myapplication.ui.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Alumno
import com.example.myapplication.data.models.Nivel
import com.example.myapplication.data.repository.NivelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlumnoViewModel(
    private val repository: NivelRepository = NivelRepository() // reutilizamos NivelRepository
) : ViewModel() {

    private val _niveles = MutableStateFlow<List<Nivel>>(emptyList())
    val niveles: StateFlow<List<Nivel>> = _niveles

    init {
        listar()
    }

    private fun listar() {
        viewModelScope.launch {
            try {
                _niveles.value = repository.listar()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun obtenerAlumnos(nivelId: Int, letraId: Int): List<Alumno> {
        val nivel = _niveles.value.find { it.id == nivelId } ?: return emptyList()
        val curso = nivel.cursos.find { it.letra.id == letraId } ?: return emptyList()

        return curso.alumnos.map { alumno ->
            alumno.copy(curso = curso) // 🔥 aquí le inyectas el curso
        }
    }
}