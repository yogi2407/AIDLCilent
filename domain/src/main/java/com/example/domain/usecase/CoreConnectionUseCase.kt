package com.example.domain.usecase

import android.util.Log
import com.example.data.interactor.IBtRepository
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.data.interactor.IDeviceRepository
import com.example.data.interactor.IRuiRepository
import com.example.data.interactor.IWifiRepository
import com.example.domain.interactor.IBtUseCase
import com.example.domain.interactor.ICoreConnectionUseCase
import com.example.domain.interactor.IDeviceUseCase
import com.example.domain.interactor.IRuiUseCase
import com.example.domain.interactor.IWifiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoreConnectionUseCase @Inject constructor(
    private val coreServiceConnection: IDataCoreServiceConnection,
    private val btRepository:IBtRepository,
    private val deviceRepository:IDeviceRepository,
    private val ruiRepository:IRuiRepository,
    private val wifiRepository:IWifiRepository
) : ICoreConnectionUseCase {

    override val coreConnectionState =  MutableStateFlow<String> ("Core Not Connected")

    init {

        CoroutineScope(Dispatchers.IO).launch {
            coreServiceConnection.dataCoreServiceConnection.collectLatest {
                if(it == "Core Service Connected") {
                    btRepository.registerBtCallback()
                    deviceRepository.registerDeviceCallback()
                    ruiRepository.registerRuiCallback()
                    wifiRepository.registerWifiCallback()
                    coreConnectionState.value = it
                    coreConnectionState.emit(it)
                    Log.d("Yogi","CoreConnectionUseCase Data Collect Data ${coreConnectionState.value}")
                }
            }
        }
    }

    override fun coreConnectionUseCaseHandler() {
        Log.d("Yogi","CoreConnectionUseCase Data Collect Data ${coreConnectionState.value}")
        coreServiceConnection.dataCoreServiceConnectionHandler()
    }
}