package com.example.myapplication.ui.screen

import androidx.compose.runtime.getValue


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.myapplication.data.models.Profesor
import com.example.myapplication.data.models.TipoPermiso
import com.example.myapplication.ui.viewModel.AlumnoViewModel
import com.example.myapplication.ui.viewModel.CursoViewModel
import com.example.myapplication.ui.viewModel.NivelViewModel
import com.example.myapplication.ui.viewModel.ProfesorViewModel
import com.example.myapplication.ui.viewModel.TipoPermisoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlumnoScreen(
    navController: NavController,
    nivelId: Int,
    letraId: Int,
    profesorId: Int,
    tipoPermisoId: Int,
    cursoViewModel: CursoViewModel
) {
    val cursos by cursoViewModel.cursos.collectAsState(initial = emptyList())
    val curso = cursoViewModel.obtenerCurso(nivelId, letraId)
    val alumnos = curso?.alumnos ?: emptyList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona Alumno") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->  // 👈 Cambia el nombre para usarlo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)  // 👈 Aplica el padding del Scaffold
                .padding(16.dp),  // 👈 Y adicionalmente 16dp
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (alumnos.isEmpty()) {
                Text("No hay alumnos en este curso")
            } else {
                alumnos.forEach { alumno ->
                    Button(
                        onClick = {
                            navController.navigate("ubicacion/${alumno.id}/$profesorId/$tipoPermisoId")
                        },
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