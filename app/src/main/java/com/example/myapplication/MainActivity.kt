package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.Profesor
import com.example.myapplication.ui.HomeScreen
import com.example.myapplication.ui.ProfesorScreen
import com.example.myapplication.ui.ProfesorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Surface(color = MaterialTheme.colorScheme.background) {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable  ("home") { HomeScreen(navController) }
                    composable("profesor") { ProfesorScreen() }
                    composable("inspector") {
                        // TODO: InspectorScreen() cuando la hagas
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaProfesores(vm: ProfesorViewModel = viewModel()) {
    LaunchedEffect(Unit) { vm.cargar() }

    when {
        vm.cargando -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        vm.error != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Error: ${vm.error}")
                Button(onClick = { vm.cargar() }) { Text("Reintentar") }
            }
        }
        else -> ListaProfesores(vm.profesores)
    }
}

@Composable
fun ListaProfesores(items: List<Profesor>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items) { p ->
            CardProfesor(p)
        }
    }
}

@Composable
fun CardProfesor(p: Profesor) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Text(p.nombre, style = MaterialTheme.typography.titleMedium)
    }
}
