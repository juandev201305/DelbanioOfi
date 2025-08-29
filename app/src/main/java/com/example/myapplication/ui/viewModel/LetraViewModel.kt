package com.example.myapplication.ui.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Letra
import com.example.myapplication.data.repository.LetraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LetraViewModel(
    private val repository: LetraRepository = LetraRepository()
) : ViewModel() {

    private val _letras = MutableStateFlow<List<Letra>>(emptyList())
    val letras: StateFlow<List<Letra>> = _letras

    init {
        listarLetras()
    }

    private fun listarLetras() {
        viewModelScope.launch {
            try {
                val data = repository.listar()
                _letras.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}