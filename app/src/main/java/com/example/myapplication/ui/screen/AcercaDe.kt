package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image // Importa el composable Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource // Importa painterResource para cargar la imagen
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R // Asegúrate de que esta sea la ruta correcta a tus recursos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcercaDe(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acerca de la App") },
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Aquí se agrega la imagen logochicot
            Image(
                painter = painterResource(id = R.drawable.logotr), // Asegúrate de que "logochicot" sea el nombre correcto del recurso
                contentDescription = "Logo de la aplicación",
                modifier = Modifier.size(150.dp) // Tamaño de 150dp (ancho y alto)
            )

            Spacer(modifier = Modifier.height(24.dp)) // Espacio después de la imagen

            Text(
                "Esta aplicación fue desarrollada por: Juan I. Correa Paredes del 4E del año 2025, con la ayuda del Profesor_De_Programación del Liceo Comercial B-72 de Estación Central Mauricio A. Saavedra Campos. Nació bajo la idea hacer más práctica y eficaz el {método} de solicitar permisos de alumnos a dirigirse a otras áreas del establecimiento durante las horas de clases.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "El nombre de la aplicación fue bajo la pronunciación constante al llamado de la misma (...hay que terminar la aplicación del baño). Se opto por darle el nombre DelBanio sin la consonante Ñ, para referenciar que fue diseñada desde un punto pedagógico informático, donde la letra Ñ no se suele utilizar.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "La composición del nombre en letra minúscula y mayúscula es en referencia a la convención camelCase así como algunas frases dentro de la app que se encuentran en Snake_Case.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "El logo delBanio tiene tres puntos de enfoque, el color celeste lado izquierdo que refiere al cielo pensando que vienen los alumnos desde áreas de espacio abierto. El color verde con una escuadra de una puerta a un espacio donde se requiere asistir. El símbolo feliz de un WC y el gorro académico se utilizaron para dar un enfoque estudiantil.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Agradecimientos: Yo Mauricio, agradezco cariñosamente a mi alumno Juan C, por devolverme los ánimos de idear aplicaciones y la compañía durante el proceso de desarrollo. También a los directivos por otorgar los permisos necesarios y apoyo en la creación de esta app.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Esta versión delBanio(B72) es en donación y con la intención de promover la programación. Todos los derechos reservados 2025",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
