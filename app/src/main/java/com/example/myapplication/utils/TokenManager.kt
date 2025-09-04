package com.example.myapplication.utils

import android.content.Context

object TokenManager {
    private const val PREFS_NAME = "prefs_fcm"
    private const val KEY_TOKEN = "fcm_token"
    private const val KEY_ACTIVO = "fcm_activo"

    fun guardarToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_TOKEN, token)
            .putBoolean(KEY_ACTIVO, true)
            .apply()
    }

    fun desactivarToken(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(KEY_ACTIVO, false)
            .apply()
    }

    fun obtenerToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_TOKEN, null)
    }

    fun estaActivo(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ACTIVO, false)
    }
}