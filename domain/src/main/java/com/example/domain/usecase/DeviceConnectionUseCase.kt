package com.example.domain.usecase

import android.util.Log
import com.example.data.interactor.IDeviceRepository
import com.example.domain.interactor.IDeviceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeviceConnectionUseCase @Inject constructor(
    private val deviceRepositoryImpl: IDeviceRepository
    ) : IDeviceUseCase {

    override val deviceToggleState = MutableStateFlow<Boolean>(false)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            deviceRepositoryImpl.deviceToggleState.collectLatest {
                Log.d("Yogi","DeviceConnectionUseCase deviceToggleState Collected $it")
                deviceToggleState.emit(it)
            }
        }
    }

    override suspend fun deviceToggleUseCaseHandler() {
        withContext(Dispatchers.Default) {
            Log.d("Yogi","DeviceConnectionUseCase deviceToggleUseCaseHandler Called")
            deviceRepositoryImpl.onDeviceToggleClicked()
        }
    }

}