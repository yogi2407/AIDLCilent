package com.example.data.interactor

import kotlinx.coroutines.flow.StateFlow

interface IRuiRepository {

    val ruiToggleState: StateFlow<Boolean>

    fun registerRuiCallback()

    suspend fun onRuiToggleClicked()
}