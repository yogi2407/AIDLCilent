package com.example.domain.interactor

import kotlinx.coroutines.flow.StateFlow

interface IBtUseCase {

    val btToggleState: StateFlow<Boolean>

    suspend fun btToggleUseCaseHandler()

}