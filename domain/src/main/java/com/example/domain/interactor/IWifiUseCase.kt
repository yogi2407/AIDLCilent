package com.example.domain.interactor

import kotlinx.coroutines.flow.StateFlow

interface IWifiUseCase {

    val wifiToggleState: StateFlow<Boolean>

    suspend fun wifiToggleUseCaseHandler()

}