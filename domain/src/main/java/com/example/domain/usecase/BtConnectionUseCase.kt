package com.example.domain.usecase

import android.util.Log
import com.example.data.interactor.IBtRepository
import com.example.domain.interactor.IBtUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BtConnectionUseCase @Inject constructor(
    private val btRepositoryImpl: IBtRepository
    ) : IBtUseCase {

    override val btToggleState = MutableStateFlow<Boolean>(false)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            btRepositoryImpl.btToggleState.collectLatest {
                Log.d("Yogi","BtConnectionUseCase btToggleState Collected $it")
                btToggleState.emit(it)
            }
        }
    }

    override suspend fun btToggleUseCaseHandler() {
        Log.d("yogi","btToggleUseCaseHandler ThreadName: ${Thread.currentThread().name}")

        withContext(Dispatchers.Default) {
            Log.d("yogi","btToggleUseCaseHandler Inside ThreadName: ${Thread.currentThread().name}")
            Log.d("Yogi","BtConnectionUseCase btToggleUseCaseHandler Called")
            btRepositoryImpl.onBtToggleClicked()
        }
    }
}