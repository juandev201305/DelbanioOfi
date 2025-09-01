package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.data.models.Inspector
import com.example.myapplication.data.repository.NotificacionRepository
import com.example.myapplication.ui.viewModel.InspectorViewModel
import com.example.myapplication.ui.viewModel.NotificacionViewModel
import androidx.compose.runtime.getValue
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorScreen(
    navController: NavController? = null,
    vm: InspectorViewModel = viewModel()
) {
    val mensaje by vm.mensaje.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inspector") },
                navigationIcon = {
                    navController?.let {
                        IconButton(onClick = { it.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Atrás"
                            )
                        }
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
            verticalArrangement = Arrangement.Top
        ) {
            if (mensaje == null) {
                Text(
                    "Esperando mensajes...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Alumno: ${mensaje!!.alumno}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Curso: ${mensaje!!.curso}")
                        Text("Profesor: ${mensaje!!.profesor}")
                        Text("Permiso: ${mensaje!!.permiso}")
                        Text("Hora salida: ${mensaje!!.horaSalida}")
                        Text("Ubicación: ${mensaje!!.ubicacion}")
                    }
                }
            }
        }
    }
}