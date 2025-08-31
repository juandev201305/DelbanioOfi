package com.example.myapplication.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.data.models.*
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.ui.viewModel.UbicacionViewModel
import com.example.myapplication.data.repository.MensajeEntrRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UbicacionScreen(
    navController: NavController,
    alumno: Alumno,
    profesor: Profesor,
    tipoPermiso: TipoPermiso
) {
    val viewModel: UbicacionViewModel = viewModel()
    val ubicaciones by viewModel.ubicaciones.collectAsState()
    val mensajeRepo = remember { MensajeEntrRepository(ApiClient.api) }
    val scope = rememberCoroutineScope()

    var sending by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) } // 👈 estado para el AlertDialog

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona Ubicación") },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (ubicaciones.isEmpty()) {
                Text("Cargando ubicaciones...")
            } else {
                ubicaciones.forEach { ubicacion ->
                    Button(
                        onClick = {
                            val fechaHora = SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss",
                                Locale.getDefault()
                            ).format(Date())

                            scope.launch {
                                sending = true
                                try {
                                    val response = mensajeRepo.enviarMensajeEntr(
                                        alumno = alumno,
                                        curso = alumno.curso!!,
                                        permiso = tipoPermiso,
                                        profesorId = profesor.id,
                                        inspectorId = 0,
                                        ubicacionId = ubicacion.id,
                                        hora = fechaHora
                                    )

                                    if (response.isSuccessful) {
                                        showDialog = true // 👈 muestra el dialogo
                                    } else {
                                        error = "Error en el servidor: ${response.code()}"
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    error = "Error de conexión"
                                } finally {
                                    sending = false
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.7f),
                        enabled = !sending
                    ) {
                        Text(ubicacion.nombre)
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }

            if (sending) {
                Spacer(Modifier.height(20.dp))
                CircularProgressIndicator()
            }

            error?.let {
                Spacer(Modifier.height(20.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }

        // 🚨 Aquí el AlertDialog de Compose
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { /* No se cierra tocando afuera */ },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        navController.navigate("home") { // 👈 ajusta el nombre de tu ruta
                            popUpTo(0) { inclusive = true } // limpia el backstack
                        }
                    }) {
                        Text("OK")
                    }
                },
                title = { Text("PERMISO RECIBIDO") },
                text = { Text("El permiso fue enviado correctamente.") }
            )
        }
    }
}
