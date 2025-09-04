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
import androidx.navigation.NavController
import com.example.myapplication.ui.viewModel.CursoViewModel
import androidx.compose.runtime.getValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NivelScreen(
    navController: NavController,
    profesorId: Int,
    tipoPermisoId: Int,
    viewModel: CursoViewModel
) {
    val cursos by viewModel.cursos.collectAsState(initial = emptyList())
    val niveles = viewModel.obtenerNiveles()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona Nivel") },
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
            if (niveles.isEmpty()) {
                Text("Cargando niveles...")
            } else {
                niveles.forEach { nivel ->
                    Button(
                        onClick = {
                            navController.navigate("letras/${nivel.id}/$profesorId/$tipoPermisoId")
                        },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Text("Nivel: ${nivel.nivel}")
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}