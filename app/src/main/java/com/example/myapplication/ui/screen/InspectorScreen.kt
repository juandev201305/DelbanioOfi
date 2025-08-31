package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.data.models.Inspector
import com.example.myapplication.data.repository.NotificacionRepository
import com.example.myapplication.ui.viewModel.InspectorViewModel
import com.example.myapplication.ui.viewModel.NotificacionViewModel
import androidx.compose.runtime.getValue
@Composable
fun InspectorScreen() {
    val message by NotificacionRepository.lastMessage.collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("🔍 Buscando nuevos permisos...")

        if (message != null) {
            Text("📩 Último permiso recibido: $message")
        }
    }
}