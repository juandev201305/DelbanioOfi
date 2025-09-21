package com.example.myapplication.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items   // ✅ IMPORT CORRECTO
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.data.models.InspectorToken
import com.example.myapplication.ui.viewModel.InspectorViewModel
import com.example.myapplication.ui.viewModel.MensajeSaliDiarioViewModel
import com.example.myapplication.utils.TokenManager
import com.google.firebase.messaging.FirebaseMessaging
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorScreen(
    inspectorViewModel: InspectorViewModel,   // 👈 renombrado aquí
    navController: NavController,
    mensajesViewModel: MensajeSaliDiarioViewModel = viewModel()
) {
    val estadoServicio by inspectorViewModel.estado.collectAsState()
    var tokenFCM by remember { mutableStateOf("") }
    val context = LocalContext.current

    val mensajes by mensajesViewModel.mensajes.collectAsState()

    LaunchedEffect(Unit) {
        mensajesViewModel.cargarMensajes()

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                tokenFCM = task.result
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Activar Notificaciones") },
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = estadoServicio)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (tokenFCM.isNotEmpty()) {
                        val token = InspectorToken( token = tokenFCM, activo = true)
                        inspectorViewModel.activarToken(token)
                        TokenManager.guardarToken(context, tokenFCM)
                    }
                }
            ) { Text("Activar notificaciones") }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    if (tokenFCM.isNotEmpty()) {
                        inspectorViewModel.desactivarToken(tokenFCM)
                        TokenManager.desactivarToken(context)
                    }
                }
            ) { Text("Desactivar notificaciones") }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Permisos solicitados",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mensajes) { mensaje ->
                    var mostrarDialogo by remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { mostrarDialogo = true }, // 👈 al tocar el card se abre el diálogo
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // Nombre y hora en la misma fila
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = mensaje.alumno,
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = mensaje.horaSalida.trim(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    maxLines = 1,
                                    softWrap = false
                                )
                            }

                            // Resto de la info resumida
                            Text("Curso: ${mensaje.curso}", style = MaterialTheme.typography.bodyMedium)
                            Text("Profesor: ${mensaje.profesor}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    // 👇 Ventana emergente con toda la info
                    if (mostrarDialogo) {
                        AlertDialog(
                            onDismissRequest = { mostrarDialogo = false },
                            title = { Text("Detalle de salida") },
                            text = {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text("Alumno: ${mensaje.alumno}")
                                    Text("Hora: ${mensaje.horaSalida.trim()}")
                                    Text("Curso: ${mensaje.curso}")
                                    Text("Profesor: ${mensaje.profesor}")
                                    Text("Permiso: ${mensaje.permiso}")
                                    Text("Ubicación: ${mensaje.ubicacion}")
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = { mostrarDialogo = false }) {
                                    Text("Cerrar")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}