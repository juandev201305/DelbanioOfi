package com.example.myapplication.ui.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Nivel
import com.example.myapplication.data.repository.NivelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NivelViewModel(
    private val repository: NivelRepository = NivelRepository()
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

    fun obtenerLetrasPorNivel(nivelId: Int) =
        _niveles.value.find { it.id == nivelId }?.cursos?.map { it.letra }?.distinctBy { it.id } ?: emptyList()

    fun obtenerAlumnos(nivelId: Int, letraId: Int) =
        _niveles.value.find { it.id == nivelId }?.cursos?.find { it.letra.id == letraId }?.alumnos ?: emptyList()
}