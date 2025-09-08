package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.MensajeSaliDiario
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.repository.MensajeSaliDiarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MensajeSaliDiarioViewModel(
    private val repo: MensajeSaliDiarioRepository = MensajeSaliDiarioRepository(ApiClient.api)
) : ViewModel() {

    private val _mensajes = MutableStateFlow<List<MensajeSaliDiario>>(emptyList())
    val mensajes = _mensajes.asStateFlow()

    fun cargarMensajes() {
        viewModelScope.launch {
            try {
                _mensajes.value = repo.listar()
            } catch (e: Exception) {
                _mensajes.value = emptyList()
            }
        }
    }
}