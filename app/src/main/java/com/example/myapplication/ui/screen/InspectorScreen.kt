package com.example.myapplication.ui.screen

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.myapplication.service.InspectorService
import com.example.myapplication.utils.NotificationHelper
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorScreen(
    navController: NavController? = null
) {
    val context = LocalContext.current
    var estadoServicio by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inspector Panel") },
                navigationIcon = {
                    navController?.let {
                        IconButton(onClick = { it.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Atrás"
                            )
                        }
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
            verticalArrangement = Arrangement.Top
        ) {
            // Estado actual
            Text(
                text = estadoServicio,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(32.dp))

            // 🔹 Botón ACTIVAR
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val intent = Intent(context, InspectorService::class.java)
                    ContextCompat.startForegroundService(context, intent)
                    estadoServicio = "Notificaciones activadas"
                }
            ) {
                Text("Activar notificaciones")
            }

            Spacer(Modifier.height(16.dp))

            // 🔹 Botón DESACTIVAR
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    context.stopService(Intent(context, InspectorService::class.java))
                    estadoServicio = "Notificaciones desactivadas"
                }
            ) {
                Text("Desactivar notificaciones")
            }
        }
    }
}