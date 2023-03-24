package com.example.domain.usecase

import android.util.Log
import com.example.data.interactor.IRuiRepository
import com.example.domain.interactor.IRuiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RuiConnectionUseCase @Inject constructor(
    private val ruiRepository: IRuiRepository
    ) : IRuiUseCase {

    override val ruiToggleState = MutableStateFlow<Boolean>(false)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            ruiRepository.ruiToggleState.collectLatest {
                Log.d("Yogi","RuiConnectionUseCase ruiToggleState Collected $it")
                ruiToggleState.emit(it)
            }
        }
    }

    override suspend fun ruiToggleUseCaseHandler() {
        withContext(Dispatchers.Default) {
            Log.d("Yogi","RuiConnectionUseCase ruiToggleUseCaseHandler Called")
            ruiRepository.onRuiToggleClicked()
        }
    }
}