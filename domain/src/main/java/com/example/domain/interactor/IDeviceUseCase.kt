package com.example.domain.interactor

import kotlinx.coroutines.flow.StateFlow

interface IDeviceUseCase {

    val deviceToggleState: StateFlow<Boolean>

    suspend fun deviceToggleUseCaseHandler()

}