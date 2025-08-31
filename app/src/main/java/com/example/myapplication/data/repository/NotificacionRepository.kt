package com.example.myapplication.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NotificacionRepository {
    private val _lastMessage = MutableStateFlow<String?>(null)
    val lastMessage: StateFlow<String?> = _lastMessage

    fun updateMessage(msg: String?) {
        _lastMessage.value = msg
    }
}