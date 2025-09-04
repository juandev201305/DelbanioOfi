package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.models.MensajeSali
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LiceoApi

class MensajeSaliRepository(
    private val api: LiceoApi = ApiClient.api
) {

}