package com.example.data.repository

import android.os.IBinder
import android.util.Log
import com.example.data.interactor.IBtRepository
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.myaidllibrary.AidlBtCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BtRepositoryImpl @Inject constructor(
    private val coreServiceConnection: IDataCoreServiceConnection
) : IBtRepository {

    override val btToggleState =  MutableStateFlow<Boolean>(false)

    override fun registerBtCallback() {
        Log.d("Yogi","BtRepositoryImpl registerBtCallback Called")
        coreServiceConnection.btConnectionHelperInstance()?.registerBTCallbacks(mBtCallback)
    }

    override suspend fun onBtToggleClicked() {
        withContext(Dispatchers.IO) {
            Log.d("Yogi","BtRepositoryImpl onBtToggleClicked Called")
            coreServiceConnection.btConnectionHelperInstance()?.toggleBTSwitch()
        }
    }

    private val mBtCallback: AidlBtCallback.Stub = object : AidlBtCallback.Stub() {

        override fun NotifyBtCb(btData: Int) {
            Log.d("Yogi","RuiRepositoryImpl NotifyBtCb Called $btData")
            CoroutineScope(Dispatchers.Default).launch {
                if(btData == 1) btToggleState.emit(true)
                else btToggleState.emit(false)
            }
        }
    }
}