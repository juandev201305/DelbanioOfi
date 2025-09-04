package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Inspector
import com.example.myapplication.data.network.ApiClient

import com.example.myapplication.data.models.InspectorToken
import com.example.myapplication.data.network.LiceoApi

class InspectorRepository(private val api: LiceoApi) {

    suspend fun activarToken(token: InspectorToken) = api.activarToken(token)

    suspend fun desactivarToken(token: String) = api.desactivarToken(token)


}