package com.example.domain.interactor

import android.content.Context
import kotlinx.coroutines.flow.StateFlow

interface ICoreConnectionUseCase {

    val coreConnectionState:StateFlow<String>

    fun coreConnectionUseCaseHandler()

}