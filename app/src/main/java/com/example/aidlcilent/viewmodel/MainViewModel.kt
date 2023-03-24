package com.example.aidlcilent.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.IBtUseCase
import com.example.domain.interactor.ICoreConnectionUseCase
import com.example.domain.interactor.IDeviceUseCase
import com.example.domain.interactor.IRuiUseCase
import com.example.domain.interactor.IWifiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coreConnectionUseCase: ICoreConnectionUseCase,
    private val btUseCase: IBtUseCase,
    private val deviceUseCase: IDeviceUseCase,
    private val ruiUseCase:IRuiUseCase,
    private val wifiUseCase:IWifiUseCase
): ViewModel() {

    private val coreConnectionState  = MutableStateFlow("Core is not connected")
    val btToggleState  = MutableStateFlow(false)
    val deviceToggleState  = MutableStateFlow(false)
    val ruiToggleState  = MutableStateFlow(false)
    val wifiToggleState  = MutableStateFlow(false)


    val coreConnectionStateChanged : StateFlow<String> = coreConnectionState

    init {
        Log.d("Yogi","MainViewModel Called")
        viewModelScope.launch {
            coreConnectionUseCase.coreConnectionState.collect {
                coreConnectionState.value = it
                Log.d("Yogi","MainViewModel coreConnectionState Collect $coreConnectionStateChanged")
            }
        }

        viewModelScope.launch {
            btUseCase.btToggleState.collect {
                Log.d("Yogi","MainViewModel btToggleState Collect $it")
                btToggleState.emit(it)
            }
        }

        viewModelScope.launch {
            deviceUseCase.deviceToggleState.collect {
                Log.d("Yogi","MainViewModel deviceToggleState Collect $it")
                deviceToggleState.emit(it)
            }
        }

        viewModelScope.launch {
            ruiUseCase.ruiToggleState.collect {
                Log.d("Yogi","MainViewModel ruiToggleState Collect $it")
                ruiToggleState.emit(it)
            }
        }

        viewModelScope.launch {
            wifiUseCase.wifiToggleState.collect {
                Log.d("Yogi","MainViewModel wifiToggleState Collect $it")
                wifiToggleState.emit(it)
            }
        }
    }

    fun onConnectCoreService() {
        Log.d("Yogi","onConnectCoreService Called")
        coreConnectionUseCase.coreConnectionUseCaseHandler()
    }

    fun onBtToggleClicked() {
        Log.d("Yogi","onBtToggleClicked Called")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("yogi","ThreadName: ${Thread.currentThread().name}")
            btUseCase.btToggleUseCaseHandler()
        }
    }

    fun onDeviceToggleClicked() {
        Log.d("Yogi","onDeviceToggleClicked Called")
        viewModelScope.launch {
            deviceUseCase.deviceToggleUseCaseHandler()
        }
    }

    fun onRuiToggleClicked() {
        Log.d("Yogi","onRuiToggleClicked Called")
        viewModelScope.launch {
            ruiUseCase.ruiToggleUseCaseHandler()
        }
    }

    fun onWifiToggleClicked() {
        Log.d("Yogi","onWifiToggleClicked Called")
        viewModelScope.launch {
            wifiUseCase.wifiToggleUseCaseHandler()
        }
    }

}