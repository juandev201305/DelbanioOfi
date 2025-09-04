package com.example.myapplication.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.models.InspectorToken
import com.example.myapplication.ui.viewModel.InspectorViewModel
import com.example.myapplication.utils.TokenManager
import com.google.firebase.messaging.FirebaseMessaging


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorScreen(viewModel: InspectorViewModel, navController: NavController) {
    val estadoServicio by viewModel.estado.collectAsState()
    var tokenFCM by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Obtener token al cargar la pantalla
    LaunchedEffect(Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                tokenFCM = task.result

                // 🔹 Si ya estaba activo antes, actualizar estado en la pantalla
                if (TokenManager.estaActivo(context)) {
                    viewModel.setEstado("Token activado")
                }
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center, // 👈 centra vertical
            horizontalAlignment = Alignment.CenterHorizontally // 👈 centra horizontal
        ) {
            Text(text = estadoServicio)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (tokenFCM.isNotEmpty()) {
                        val token = InspectorToken(
                            idInspector = 1, // 👈 cámbialo si corresponde
                            token = tokenFCM,
                            activo = true
                        )
                        viewModel.activarToken(token)

                        // Guardar en local
                        TokenManager.guardarToken(context, tokenFCM)
                    }
                }
            ) {
                Text("Activar notificaciones")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    if (tokenFCM.isNotEmpty()) {
                        viewModel.desactivarToken(tokenFCM)

                        // Desactivar en local
                        TokenManager.desactivarToken(context)
                    }
                }
            ) {
                Text("Desactivar notificaciones")
            }
        }
    }
}