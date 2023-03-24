package com.example.data.interactor

import kotlinx.coroutines.flow.StateFlow

interface IWifiRepository {

    val wifiToggleState: StateFlow<Boolean>

    fun registerWifiCallback()

    suspend fun onWifiToggleClicked()
}