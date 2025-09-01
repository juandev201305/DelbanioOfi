package com.example.myapplication.service


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.data.repository.MensajeSaliRepository
import com.example.myapplication.utils.NotificationHelper
import kotlinx.coroutines.*

class InspectorService : Service() {

    private var job: Job? = null
    private val repo = MensajeSaliRepository()
    private var lastId: Int? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(1, buildNotification("Monitoreando alumnos..."))

        job = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                try {
                    val ultimo = repo.fetchUltimoMensaje()
                    if (ultimo != null && ultimo.id != lastId) {
                        lastId = ultimo.id
                        NotificationHelper.showNotification(
                            this@InspectorService,
                            "Alumno llegando en ${ultimo.permiso}",
                            "${ultimo.alumno} - ${ultimo.curso}"
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(5000) // cada 10 segundos
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    private fun buildNotification(content: String): Notification {
        val channelId = "inspector_service"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Inspector Monitor",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Inspector en ejecución")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true) // Notificación permanente
            .build()
    }
}