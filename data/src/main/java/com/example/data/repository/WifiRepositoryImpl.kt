package com.example.data.repository

import android.os.IBinder
import android.util.Log
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.data.interactor.IWifiRepository
import com.example.myaidllibrary.AidlBtCallback
import com.example.myaidllibrary.AidlWifiCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WifiRepositoryImpl @Inject constructor(
    private val coreServiceConnection: IDataCoreServiceConnection
) : IWifiRepository{

    override val wifiToggleState =  MutableStateFlow<Boolean>(false)

    override fun registerWifiCallback() {
        Log.d("Yogi","WifiRepositoryImpl registerWifiCallback Called")
        coreServiceConnection.wifiConnectionHelperInstance()?.registerWifiCallback(mWifiCallback)
    }

    override suspend fun onWifiToggleClicked() {
        withContext(Dispatchers.IO) {
            Log.d("Yogi","WifiRepositoryImpl onWifiToggleClicked Called")
            coreServiceConnection.wifiConnectionHelperInstance()?.toggleWifiSwitch()
        }
    }

    private val mWifiCallback:AidlWifiCallback.Stub = object :AidlWifiCallback.Stub() {

        override fun NotifyWifiCb(wifiState: Int) {
            Log.d("Yogi","WifiRepositoryImpl NotifyWifiCb $wifiState")
            CoroutineScope(Dispatchers.Default).launch {
                if(wifiState == 1) {
                    wifiToggleState.emit(true)
                } else wifiToggleState.emit(false)
            }
        }
    }
}