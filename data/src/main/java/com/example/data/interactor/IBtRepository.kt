package com.example.data.interactor

import kotlinx.coroutines.flow.StateFlow

interface IBtRepository {

    val btToggleState: StateFlow<Boolean>

    fun registerBtCallback()

    suspend fun onBtToggleClicked()
}