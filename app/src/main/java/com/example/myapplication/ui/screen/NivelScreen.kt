package com.example.myapplication.ui.screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.myapplication.data.models.Curso
import com.example.myapplication.data.models.Nivel
import com.example.myapplication.ui.viewModel.NivelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NivelScreen(
    navController: NavController,
    profesorId: Int,
    tipoPermisoId: Int,
    viewModel: NivelViewModel
) {
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
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("letras/1/$profesorId/$tipoPermisoId") }) {
                Text("Primero Medio")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { navController.navigate("letras/2/$profesorId/$tipoPermisoId") }) {
                Text("Segundo Medio")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { navController.navigate("letras/3/$profesorId/$tipoPermisoId") }) {
                Text("Tercero Medio")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { navController.navigate("letras/4/$profesorId/$tipoPermisoId") }) {
                Text("Cuarto Medio")
            }
        }
    }
}
