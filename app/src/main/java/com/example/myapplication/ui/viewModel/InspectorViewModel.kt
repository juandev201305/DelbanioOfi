package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.models.MensajeSali
import com.example.myapplication.data.repository.MensajeSaliRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InspectorViewModel(
    private val repo: MensajeSaliRepository = MensajeSaliRepository()
) : ViewModel() {

    private val _mensaje = MutableStateFlow<MensajeSali?>(null)
    val mensaje: StateFlow<MensajeSali?> = _mensaje

    private var lastId: Int? = null

    suspend fun fetchUltimo(): MensajeSali? {
        return try {
            val ultimo = repo.fetchUltimoMensaje()
            if (ultimo != null && ultimo.id != lastId) {
                _mensaje.value = ultimo
                lastId = ultimo.id
            }
            ultimo
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}