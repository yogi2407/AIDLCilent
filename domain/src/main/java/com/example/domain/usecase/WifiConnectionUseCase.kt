package com.example.domain.usecase

import android.util.Log
import com.example.data.interactor.IWifiRepository
import com.example.domain.interactor.IWifiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WifiConnectionUseCase @Inject constructor(
    private val wifiRepository:IWifiRepository
    ) : IWifiUseCase {

    override val wifiToggleState = MutableStateFlow<Boolean>(false)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            wifiRepository.wifiToggleState.collectLatest {
                Log.d("Yogi","WifiConnectionUseCase wifiToggleState Collected $it")
                wifiToggleState.emit(it)
            }
        }
    }

    override suspend fun wifiToggleUseCaseHandler() {
        withContext(Dispatchers.Default) {
            Log.d("Yogi","BtConnectionUseCase btToggleUseCaseHandler Called")
            wifiRepository.onWifiToggleClicked()
        }
    }


}