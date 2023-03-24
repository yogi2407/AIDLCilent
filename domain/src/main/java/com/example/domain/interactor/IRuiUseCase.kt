package com.example.domain.interactor

import kotlinx.coroutines.flow.StateFlow

interface IRuiUseCase {

    val ruiToggleState: StateFlow<Boolean>

    suspend fun ruiToggleUseCaseHandler()

}