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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NextScreenViewModel @Inject constructor(
    private val btUseCase: IBtUseCase,
    private val deviceUseCase: IDeviceUseCase,
    private val ruiUseCase:IRuiUseCase,
    private val wifiUseCase:IWifiUseCase
): ViewModel() {

    val btToggleState  = MutableStateFlow(false)
    val deviceToggleState  = MutableStateFlow(false)
    val ruiToggleState  = MutableStateFlow(false)
    val wifiToggleState  = MutableStateFlow(false)



    init {
        Log.d("Yogi","NextScreenViewModel Called")

        viewModelScope.launch {
            btUseCase.btToggleState.collect {
                Log.d("Yogi","NextScreenViewModel btToggleState Collect $it")
                btToggleState.emit(it)
            }
        }

        viewModelScope.launch {
            deviceUseCase.deviceToggleState.collect {
                Log.d("Yogi","NextScreenViewModel deviceToggleState Collect $it")
                deviceToggleState.emit(it)
            }
        }

        viewModelScope.launch {
            ruiUseCase.ruiToggleState.collect {
                Log.d("Yogi","NextScreenViewModel ruiToggleState Collect $it")
                ruiToggleState.emit(it)
            }
        }

        viewModelScope.launch {
            wifiUseCase.wifiToggleState.collect {
                Log.d("Yogi","NextScreenViewModel wifiToggleState Collect $it")
                wifiToggleState.emit(it)
            }
        }
    }

   fun onBtToggleClicked() {
        Log.d("Yogi","onBtToggleClicked Called")
        viewModelScope.launch {
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