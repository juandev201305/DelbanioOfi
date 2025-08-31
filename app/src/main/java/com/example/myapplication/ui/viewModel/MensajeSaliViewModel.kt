package com.example.myapplication.ui.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.MensajeSali
import com.example.myapplication.data.repository.MensajeSaliRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MensajeSaliViewModel(
    private val repository: MensajeSaliRepository = MensajeSaliRepository()
) : ViewModel() {

    private val _ultimoMensaje = MutableStateFlow<MensajeSali?>(null)
    val ultimoMensaje: StateFlow<MensajeSali?> = _ultimoMensaje

    fun startListening() {
        viewModelScope.launch {
            while (true) {
                try {
                    val nuevo = repository.getUltimoMensaje()
                    _ultimoMensaje.value = nuevo
                } catch (e: Exception) {
                    // si no hay nada nuevo, no pasa nada
                }
                delay(5000) // cada 5 segundos consulta el backend
            }
        }
    }
}