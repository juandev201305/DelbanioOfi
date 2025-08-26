package com.example.myapplication.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Profesor
import com.example.myapplication.data.ProfesorRepository
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfesorViewModel (
    private val repo: ProfesorRepository = ProfesorRepository()

    ) : ViewModel() {
    var profesores by mutableStateOf<List<Profesor>>(emptyList())
        private set

    var cargando by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set
    fun cargar(){
        viewModelScope.launch {
            cargando = true
            error = null
            try{
                profesores = repo.listar()
            } catch (e: Exception){
                error = e.message ?:"error desconocido"
            } finally {
                cargando = false
            }
        }
    }

}