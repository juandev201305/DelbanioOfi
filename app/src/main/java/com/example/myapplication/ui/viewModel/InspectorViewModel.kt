package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Inspector
import com.example.myapplication.data.models.MensajeSali
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.repository.InspectorRepository
import com.example.myapplication.data.repository.MensajeSaliRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InspectorViewModel(
    private val repo: MensajeSaliRepository = MensajeSaliRepository()
) : ViewModel() {

    private val _mensaje = MutableStateFlow<MensajeSali?>(null)
    val mensaje: StateFlow<MensajeSali?> = _mensaje

    init {
        viewModelScope.launch {
            while (true) {
                try {
                    val ultimo = repo.fetchUltimoMensaje()
                    _mensaje.value = ultimo
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                kotlinx.coroutines.delay(5000)
            }
        }
    }
}