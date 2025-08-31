package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Inspector
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

data class InspectorRepository(
    private val api: LiceoApi = ApiClient.api
) {
    suspend fun getInspectores(): List<Inspector> = api.getInspectores()
}