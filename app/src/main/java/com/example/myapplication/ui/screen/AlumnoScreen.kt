package com.example.myapplication.ui.screen



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
    viewModel: AlumnoViewModel = viewModel()
) {
    val alumnos = viewModel.obtenerAlumnos(nivelId, letraId)

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
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
