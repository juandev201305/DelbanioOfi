package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.viewModel.TipoPermisoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoPermisoScreen(
    navController: NavController,
    profesorId: Int,
    viewModel: TipoPermisoViewModel = viewModel()
) {
    val permisos by viewModel.permisos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seleccione el tipo de permiso") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (permisos.isEmpty()) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    permisos.forEach { permiso ->
                        Button(
                            onClick = {
                                // Pasamos profesorId y tipoPermisoId
                                navController.navigate("niveles/$profesorId/${permiso.id}")
                            },
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        ) {
                            Text(permiso.tipo)
                        }
                    }
                }
            }
        }
    }
}
