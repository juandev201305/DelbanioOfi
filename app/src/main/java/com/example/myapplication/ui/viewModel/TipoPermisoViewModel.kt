package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.TipoPermiso
import com.example.myapplication.data.repository.TipoPermisoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TipoPermisoViewModel (
    private val repository: TipoPermisoRepository = TipoPermisoRepository()
) : ViewModel() {

    private val _permisos = MutableStateFlow<List<TipoPermiso>>(emptyList())
    val permisos: StateFlow<List<TipoPermiso>> = _permisos

    init {
        listarPermisos()
    }

    private fun listarPermisos() {
        viewModelScope.launch {
            try {
                val data = repository.listar()
                _permisos.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}