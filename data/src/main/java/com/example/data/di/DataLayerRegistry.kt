package com.example.data.di

import com.example.data.core.CoreServiceConnection
import com.example.data.interactor.IBtRepository
import com.example.data.interactor.IDataCoreServiceConnection
import com.example.data.interactor.IDeviceRepository
import com.example.data.interactor.IRuiRepository
import com.example.data.interactor.IWifiRepository
import com.example.data.repository.BtRepositoryImpl
import com.example.data.repository.DeviceRepositoryImpl
import com.example.data.repository.RuiRepositoryImpl
import com.example.data.repository.WifiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataLayerRegistry {

    @[Binds Singleton]
    abstract fun bindDataLayerRegistry(coreServiceConnection: CoreServiceConnection):IDataCoreServiceConnection

    @[Binds Singleton]
    abstract fun bindBTRepository(btRepositoryImpl: BtRepositoryImpl):IBtRepository

    @[Binds Singleton]
    abstract fun bindDeviceRepository(deviceRepositoryImpl: DeviceRepositoryImpl):IDeviceRepository

    @[Binds Singleton]
    abstract fun bindRuiRepository(ruiRepositoryImpl: RuiRepositoryImpl):IRuiRepository

    @[Binds Singleton]
    abstract fun bindWifiRepository(wifiRepositoryImpl: WifiRepositoryImpl):IWifiRepository

}