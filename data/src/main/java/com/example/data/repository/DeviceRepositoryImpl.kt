package com.example.data.repository

import android.os.IBinder
import android.util.Log
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.data.interactor.IDeviceRepository
import com.example.myaidllibrary.AidlDeviceCallback
import com.example.myaidllibrary.AidlRuiCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val coreServiceConnection: IDataCoreServiceConnection
):IDeviceRepository {

    override val deviceToggleState =  MutableStateFlow<Boolean>(false)

    override fun registerDeviceCallback() {
        Log.d("Yogi","DeviceRepositoryImpl registerDeviceCallback Called")
        coreServiceConnection.deviceConnectionHelperInstance()?.registerDeviceCallbacks(mDeviceCallback)
    }

    override suspend fun onDeviceToggleClicked() {
        withContext(Dispatchers.IO) {
            Log.d("Yogi","DeviceRepositoryImpl onDeviceToggleClicked Called")
            coreServiceConnection.deviceConnectionHelperInstance()?.toggleDeviceSwitch()
        }
    }

    private val mDeviceCallback: AidlDeviceCallback.Stub = object : AidlDeviceCallback.Stub() {

        override fun NotifyDeviceCb(deviceState: Int) {
            Log.d("Yogi","RuiRepositoryImpl NotifyDeviceCb: $deviceState")
            CoroutineScope(Dispatchers.Default).launch {
                if(deviceState == 1) deviceToggleState.emit(true)
                else deviceToggleState.emit(false)
            }
        }

    }
}