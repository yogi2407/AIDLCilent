package com.example.data.repository

import android.os.IBinder
import android.util.Log
import android.widget.ImageSwitcher
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.data.interactor.IRuiRepository
import com.example.myaidllibrary.AidlBtCallback
import com.example.myaidllibrary.AidlRuiCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RuiRepositoryImpl @Inject constructor(
    private val coreServiceConnection: IDataCoreServiceConnection
) :IRuiRepository {

    override val ruiToggleState =  MutableStateFlow<Boolean>(false)

    override fun registerRuiCallback() {
        Log.d("Yogi","RuiRepositoryImpl registerRuiCallback Called")
        coreServiceConnection.ruiConnectionHelperInstance()?.registerRUICallbacks(mRuiCallback)
    }

    override suspend fun onRuiToggleClicked() {
        withContext(Dispatchers.IO) {
            Log.d("Yogi","RuiRepositoryImpl onRuiToggleClicked Called")
           coreServiceConnection.ruiConnectionHelperInstance()?.toggleRUISwitch()
        }
    }

    private val mRuiCallback: AidlRuiCallback.Stub = object : AidlRuiCallback.Stub() {

        override fun NotifyRUICb(ruiState: Int) {
            Log.d("Yogi","RuiRepositoryImpl NotifyRUICb : $ruiState")
            CoroutineScope(Dispatchers.Default).launch {
                if(ruiState == 1) ruiToggleState.emit(true)
                else ruiToggleState.emit(false)
            }
        }

    }
}