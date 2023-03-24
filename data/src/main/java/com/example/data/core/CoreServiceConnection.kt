package com.example.data.core

import android.content.Context
import android.util.Log
import com.example.data.interactor.IBtRepository
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.data.interactor.IDeviceRepository
import com.example.data.interactor.IRuiRepository
import com.example.data.interactor.IWifiRepository
import com.example.myaidllibrary.AidlServiceHelper.BTConnectionHelper
import com.example.myaidllibrary.AidlServiceHelper.DeviceConnectionHelper
import com.example.myaidllibrary.AidlServiceHelper.RUIConnectionHelper
import com.example.myaidllibrary.AidlServiceHelper.WifiConnectionHelper
import com.example.myaidllibrary.MyAidlServiceHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton


class CoreServiceConnection @Inject constructor(
    @ApplicationContext private val context: Context,
) : IDataCoreServiceConnection,MyAidlServiceHelper.AidlServiceConnectionInterface {

    override val dataCoreServiceConnection =  MutableStateFlow<String> ("Core Not Connected")

    private var mBTConnectionHelper:BTConnectionHelper? = null
    private var mRUIConnectionHelper:RUIConnectionHelper? = null
    private var mDeviceConnectionHelper:DeviceConnectionHelper? = null
    private var mWifiConnectionHelper:WifiConnectionHelper? = null

    var isCoreServiceStatus = false

    override fun dataCoreServiceConnectionHandler() {
        Log.d("Yogi","dataCoreServiceConnectionHandler Called")
        MyAidlServiceHelper(context,this)
    }

    override fun isCoreServiceConnected(): Boolean {
        return isCoreServiceStatus
    }

    override fun btConnectionHelperInstance(): BTConnectionHelper? {
        return mBTConnectionHelper
    }

    override fun deviceConnectionHelperInstance(): DeviceConnectionHelper? {
        return mDeviceConnectionHelper
    }

    override fun ruiConnectionHelperInstance(): RUIConnectionHelper? {
        return mRUIConnectionHelper
    }

    override fun wifiConnectionHelperInstance(): WifiConnectionHelper? {
        return mWifiConnectionHelper
    }

    override fun AidlServiceConnection(myAidlServiceHelper: MyAidlServiceHelper?) {
        Log.d("Yogi","AidlServiceConnection Called")
        isCoreServiceStatus = true
        mBTConnectionHelper = myAidlServiceHelper?.btConnectionHelper
        mRUIConnectionHelper = myAidlServiceHelper?.ruiConnectionHelper
        mDeviceConnectionHelper = myAidlServiceHelper?.deviceConnectionHelper
        mWifiConnectionHelper = myAidlServiceHelper?.wifiConnectionHelper

        dataCoreServiceConnection.value = "Core Service Connected"
    }

    override fun AidlServiceDisconnection() {
        isCoreServiceStatus = false
    }


}