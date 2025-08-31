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
fun LetraScreen(
    navController: NavController,
    nivelId: Int,
    profesorId: Int,
    tipoPermisoId: Int,
    viewModel: NivelViewModel
) {
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
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            letras.forEach { letra ->
                Button(
                    onClick = {
                        navController.navigate("alumnos/$nivelId/${letra.id}/$profesorId/$tipoPermisoId")
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text("Letra: ${letra.letra}")
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
