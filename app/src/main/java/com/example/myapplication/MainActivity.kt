package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.AlumnoScreen
import com.example.myapplication.ui.screen.HomeScreen
import com.example.myapplication.ui.screen.LetraScreen
import com.example.myapplication.ui.screen.NivelScreen

import com.example.myapplication.ui.screen.ProfesorScreen
import com.example.myapplication.ui.screen.TipoPermisoScreen
import com.example.myapplication.ui.viewModel.NivelViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    val nivelViewModel: NivelViewModel = viewModel() // Compartido

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("profesor") { ProfesorScreen(navController) }
                        composable("tipoPermiso") { TipoPermisoScreen(navController) }
                        composable("niveles") { NivelScreen(navController, nivelViewModel) }

                        composable("letras/{nivelId}") { backStackEntry ->
                            val nivelId = backStackEntry.arguments?.getString("nivelId")?.toIntOrNull() ?: 0
                            LetraScreen(navController, nivelId, nivelViewModel)
                        }

                        composable("alumnos/{nivelId}/{letraId}") { backStackEntry ->
                            val nivelId = backStackEntry.arguments?.getString("nivelId")?.toIntOrNull() ?: 0
                            val letraId = backStackEntry.arguments?.getString("letraId")?.toIntOrNull() ?: 0
                            AlumnoScreen(navController, nivelId, letraId, nivelViewModel)
                        }
                    }
                }
            }
        }
    }
}