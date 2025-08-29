package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.data.models.Letra
import com.example.myapplication.ui.viewModel.NivelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetraScreen(navController: NavController, nivelId: Int, viewModel: NivelViewModel) {
    val letras = viewModel.obtenerLetrasPorNivel(nivelId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona Letra") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            letras.forEach { letra ->
                Button(
                    onClick = { navController.navigate("alumnos/$nivelId/${letra.id}") },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text("Letra: ${letra.letra}")
                }
                Spacer(Modifier.height(12.dp))
            }

            if (letras.isEmpty()) {
                Text("No hay letras disponibles")
            }
        }
    }
}