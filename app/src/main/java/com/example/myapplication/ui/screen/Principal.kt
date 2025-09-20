package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Contenido principal de la pantalla, centrado
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-50).dp), // Subida de la columna principal
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logotr),
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "¿Usted es?",
                style = MaterialTheme.typography.titleLarge,
                color = Color.DarkGray, // Color más claro que el negro
                fontWeight = FontWeight.Bold // Fuente un poco más gruesa
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("profesores") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text("Profesor")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.navigate("inspectores") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text("Inspector")
            }

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(id = R.drawable.duo1),
                contentDescription = "Logo de la aplicación (abajo)",
                modifier = Modifier
                    .size(150.dp)
            )
        }

        // 1. Texto de versión en la esquina inferior izquierda
        Text(
            text = "Versión 1.0.6",
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray, // Color más claro
            fontWeight = FontWeight.Bold, // Un poco más grueso
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )

        // 2. Botón "Acerca de..." en la esquina inferior derecha
        TextButton(
            onClick = { navController.navigate("AcercaDe") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        ) {
            Text(
                text = "Acerca de...",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}