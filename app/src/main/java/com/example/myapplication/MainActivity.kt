package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.AlumnoScreen

import com.example.myapplication.ui.screen.HomeScreen
import com.example.myapplication.ui.screen.LetraScreen
import com.example.myapplication.ui.screen.NivelScreen

import com.example.myapplication.ui.screen.ProfesorScreen
import com.example.myapplication.ui.screen.TipoPermisoScreen
import com.example.myapplication.ui.screen.UbicacionScreen
import com.example.myapplication.ui.viewModel.AlumnoViewModel
import com.example.myapplication.ui.viewModel.NivelViewModel
import com.example.myapplication.ui.viewModel.ProfesorViewModel
import com.example.myapplication.ui.viewModel.TipoPermisoViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    // Instancias globales de ViewModels
                    val nivelViewModel: NivelViewModel = viewModel()
                    val alumnoViewModel: AlumnoViewModel = viewModel()
                    val profesorViewModel: ProfesorViewModel = viewModel()
                    val tipoPermisoViewModel: TipoPermisoViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "home") {

                        // Pantalla inicial
                        composable("home") {
                            HomeScreen(navController = navController)
                        }

                        composable("profesores") {
                            ProfesorScreen(navController = navController, viewModel = profesorViewModel)
                        }

                        composable(
                            "tipoPermiso/{profesorId}",
                            arguments = listOf(navArgument("profesorId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val profesorId = backStackEntry.arguments?.getInt("profesorId") ?: 0
                            TipoPermisoScreen(navController, profesorId, tipoPermisoViewModel)
                        }

                        composable(
                            "niveles/{profesorId}/{tipoPermisoId}",
                            arguments = listOf(
                                navArgument("profesorId") { type = NavType.IntType },
                                navArgument("tipoPermisoId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val profesorId = backStackEntry.arguments?.getInt("profesorId") ?: 0
                            val tipoPermisoId = backStackEntry.arguments?.getInt("tipoPermisoId") ?: 0
                            NivelScreen(navController, profesorId, tipoPermisoId, nivelViewModel)
                        }

                        composable(
                            "letras/{nivelId}/{profesorId}/{tipoPermisoId}",
                            arguments = listOf(
                                navArgument("nivelId") { type = NavType.IntType },
                                navArgument("profesorId") { type = NavType.IntType },
                                navArgument("tipoPermisoId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val nivelId = backStackEntry.arguments?.getInt("nivelId") ?: 0
                            val profesorId = backStackEntry.arguments?.getInt("profesorId") ?: 0
                            val tipoPermisoId = backStackEntry.arguments?.getInt("tipoPermisoId") ?: 0
                            LetraScreen(navController, nivelId, profesorId, tipoPermisoId, nivelViewModel)
                        }

                        composable(
                            "alumnos/{nivelId}/{letraId}/{profesorId}/{tipoPermisoId}",
                            arguments = listOf(
                                navArgument("nivelId") { type = NavType.IntType },
                                navArgument("letraId") { type = NavType.IntType },
                                navArgument("profesorId") { type = NavType.IntType },
                                navArgument("tipoPermisoId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val nivelId = backStackEntry.arguments?.getInt("nivelId") ?: 0
                            val letraId = backStackEntry.arguments?.getInt("letraId") ?: 0
                            val profesorId = backStackEntry.arguments?.getInt("profesorId") ?: 0
                            val tipoPermisoId = backStackEntry.arguments?.getInt("tipoPermisoId") ?: 0
                            AlumnoScreen(navController, nivelId, letraId, profesorId, tipoPermisoId, alumnoViewModel)
                        }

                        composable(
                            "ubicacion/{alumnoId}/{profesorId}/{tipoPermisoId}",
                            arguments = listOf(
                                navArgument("alumnoId") { type = NavType.IntType },
                                navArgument("profesorId") { type = NavType.IntType },
                                navArgument("tipoPermisoId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val alumnoId = backStackEntry.arguments?.getInt("alumnoId") ?: 0
                            val profesorId = backStackEntry.arguments?.getInt("profesorId") ?: 0
                            val tipoPermisoId = backStackEntry.arguments?.getInt("tipoPermisoId") ?: 0

                            // 🔥 Aquí aseguramos que curso siempre se setea al alumno
                            val alumno = alumnoViewModel.niveles.value
                                .flatMap { nivel ->
                                    nivel.cursos.flatMap { curso ->
                                        curso.alumnos.map { it.copy(curso = curso) }
                                    }
                                }
                                .find { it.id == alumnoId } ?: return@composable

                            val profesor = profesorViewModel.profesores.value
                                .find { it.id == profesorId } ?: return@composable

                            val tipoPermiso = tipoPermisoViewModel.permisos.value
                                .find { it.id == tipoPermisoId } ?: return@composable

                            UbicacionScreen(navController, alumno, profesor, tipoPermiso)
                        }
                    }
                }
            }
        }
    }
}
