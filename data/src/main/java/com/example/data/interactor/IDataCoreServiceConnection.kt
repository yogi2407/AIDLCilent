package com.example.data.interactor

import com.example.myaidllibrary.AidlServiceHelper.BTConnectionHelper
import com.example.myaidllibrary.AidlServiceHelper.DeviceConnectionHelper
import com.example.myaidllibrary.AidlServiceHelper.RUIConnectionHelper
import com.example.myaidllibrary.AidlServiceHelper.WifiConnectionHelper
import kotlinx.coroutines.flow.StateFlow

interface IDataCoreServiceConnection {

    val dataCoreServiceConnection:StateFlow<String>

    fun dataCoreServiceConnectionHandler()

    fun isCoreServiceConnected() : Boolean

    fun btConnectionHelperInstance() : BTConnectionHelper?

    fun deviceConnectionHelperInstance() : DeviceConnectionHelper?

    fun ruiConnectionHelperInstance() : RUIConnectionHelper?

    fun wifiConnectionHelperInstance() : WifiConnectionHelper?

}