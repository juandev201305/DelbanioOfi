package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Inspector
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.repository.InspectorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InspectorViewModel(
    private val repository: InspectorRepository = InspectorRepository()
) : ViewModel() {

    private val _inspectores = MutableStateFlow<List<Inspector>>(emptyList())
    val inspectores: StateFlow<List<Inspector>> = _inspectores

    fun fetchInspectores() {
        viewModelScope.launch {
            try {
                _inspectores.value = repository.getInspectores()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

