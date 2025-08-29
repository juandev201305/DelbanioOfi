package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Profesor
import com.example.myapplication.data.repository.ProfesorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfesorViewModel(
    private val repository: ProfesorRepository = ProfesorRepository()
) : ViewModel() {

    private val _profesores = MutableStateFlow<List<Profesor>>(emptyList())
    val profesores: StateFlow<List<Profesor>> = _profesores

    init {
        listarProfesores()
    }

    private fun listarProfesores() {
        viewModelScope.launch {
            try {
                _profesores.value = repository.listar()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}