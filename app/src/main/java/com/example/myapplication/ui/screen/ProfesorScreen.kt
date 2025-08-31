package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.data.models.Profesor
import com.example.myapplication.ui.viewModel.ProfesorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfesorScreen(navController: NavController, viewModel: ProfesorViewModel = viewModel()) {
    val profesoresState = viewModel.profesores.collectAsState()
    val profesores = profesoresState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Profesores") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(profesores) { profesor ->
                Button(
                    onClick = { navController.navigate("tipoPermiso/${profesor.id}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(text = profesor.nombre)
                }
            }
        }
    }
}