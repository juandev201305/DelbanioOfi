package com.example.myapplication.firebase


import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.myapplication.R
class MyFirebaseService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Nuevo token generado: $token")
        // Aquí podrías guardarlo en SharedPreferences para reactivarlo después
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("FCM", "Mensaje recibido: ${message.data}")

        // 🔹 Si viene con Notification
        message.notification?.let {
            showNotification(it.title, it.body)
        }

        // 🔹 Si solo viene con data
        if (message.data.isNotEmpty()) {
            val titulo = "NUEVO PERMISO: ${message.data["permiso"] ?: ""}"
            val cuerpo = "Alumno: ${message.data["alumno"] ?: ""}\n" +
                    "Curso: ${message.data["curso"] ?: ""}\n" +
                    "Profesor: ${message.data["profesor"] ?: ""}\n" +
                    "Ubicación: ${message.data["ubicacion"] ?: ""}\n" +
                    "Hora salida: ${message.data["horaSalida"] ?: ""}" // 👈 aquí llamas a la hora
            showNotification(titulo, cuerpo)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val channelId = "default_channel"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificaciones",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title ?: "ALUMNO DIRIGIENDOSE A..")
            .setContentText(body ?: "Tienes una notificación")
            .setStyle(NotificationCompat.BigTextStyle().bigText(body)) // 👈 aquí va el truco
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}