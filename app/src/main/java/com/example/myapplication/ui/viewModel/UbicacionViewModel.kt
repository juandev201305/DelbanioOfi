package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Ubicacion
import com.example.myapplication.data.repository.UbicacionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UbicacionViewModel(
    private val repository: UbicacionRepository = UbicacionRepository()
) : ViewModel() {

    private val _ubicaciones = MutableStateFlow<List<Ubicacion>>(emptyList())
    val ubicaciones: StateFlow<List<Ubicacion>> = _ubicaciones

    init {
        listarUbicaciones()
    }

    private fun listarUbicaciones() {
        viewModelScope.launch {
            try {
                val lista = repository.listarUbicaciones()
                _ubicaciones.value = lista
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}