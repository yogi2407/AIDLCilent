package com.example.data.interactor

import kotlinx.coroutines.flow.StateFlow

interface IDeviceRepository {

    val deviceToggleState: StateFlow<Boolean>

    fun registerDeviceCallback()

    suspend fun onDeviceToggleClicked()
}