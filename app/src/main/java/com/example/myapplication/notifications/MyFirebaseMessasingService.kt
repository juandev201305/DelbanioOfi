package com.example.myapplication.notifications
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.R
import com.example.myapplication.data.repository.MensajeSaliRepository
import com.example.myapplication.data.repository.NotificacionRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title ?: "Nuevo permiso"
        val body = message.notification?.body ?: "Tienes un nuevo mensaje de salida"

        // Mostramos notificación local
        showNotification(title, body)

        // 👉 Ahora pedimos al backend el último mensaje
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val ultimoMensaje = MensajeSaliRepository().getUltimoMensaje()

                // Armamos un string legible
                val resumen = "Alumno: ${ultimoMensaje.nombre} " +
                        "(${ultimoMensaje.curso})\n" +
                        "Profesor: ${ultimoMensaje.profesor}\n" +
                        "Salida: ${ultimoMensaje.horaSalida}\n" +
                        "Ubicación: ${ultimoMensaje.ubicacion}"

                // Actualizamos repo para que la UI lo muestre
                NotificacionRepository.updateMessage(resumen)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Si quieres, puedes enviar el token al backend aquí
    }

    private fun showNotification(title: String, body: String) {
        val channelId = "permiso_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Permisos",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            NotificationManagerCompat.from(this).notify(1, notification)
        }
    }
}