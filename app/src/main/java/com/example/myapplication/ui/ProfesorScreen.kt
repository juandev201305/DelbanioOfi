package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.Profesor

@Composable
fun ProfesorScreen(viewModel: ProfesorViewModel = viewModel()) {
    val profesores = viewModel.profesores
    val cargando = viewModel.cargando
    val error = viewModel.error

    // Apenas se abre la pantalla, dispara la carga
    LaunchedEffect(Unit) {
        viewModel.cargar()
    }

    when {
        cargando -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: $error")
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(profesores) { profesor ->
                    ProfesorCard(profesor)
                }
            }
        }
    }
}

@Composable
fun ProfesorCard(profesor: Profesor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = profesor.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = "ID: ${profesor.id}")
        }
    }
}
