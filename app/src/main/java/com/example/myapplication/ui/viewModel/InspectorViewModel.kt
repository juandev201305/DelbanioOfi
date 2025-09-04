package com.example.myapplication.ui.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.InspectorToken
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.repository.InspectorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InspectorViewModel(
    private val repo: InspectorRepository = InspectorRepository(ApiClient.api)
) : ViewModel() {

    private val _estado = MutableStateFlow("Servicio inactivo")
    val estado = _estado.asStateFlow()

    fun activarToken(token: InspectorToken) {
        viewModelScope.launch {
            try {
                repo.activarToken(token)
                _estado.value = "Token activado"
            } catch (e: Exception) {
                _estado.value = "Error al activar: ${e.message}"
            }
        }
    }

    fun desactivarToken(token: String) {
        viewModelScope.launch {
            try {
                repo.desactivarToken(token)
                _estado.value = "Token desactivado"
            } catch (e: Exception) {
                _estado.value = "Error al desactivar: ${e.message}"
            }
        }
    }
    fun setEstado(nuevoEstado: String) {
        _estado.value = nuevoEstado
    }
}
