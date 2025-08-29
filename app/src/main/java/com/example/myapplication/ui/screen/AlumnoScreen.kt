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
import com.example.myapplication.ui.viewModel.NivelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlumnoScreen(navController: NavController, nivelId: Int, letraId: Int, viewModel: NivelViewModel) {
    val alumnos = viewModel.obtenerAlumnos(nivelId, letraId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alumnos") },
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
            if (alumnos.isEmpty()) {
                Text("No hay alumnos en esta letra")
            } else {
                alumnos.forEach { alumno ->
                    Button(
                        onClick = { /* acción opcional */ },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(alumno.nombre)
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}