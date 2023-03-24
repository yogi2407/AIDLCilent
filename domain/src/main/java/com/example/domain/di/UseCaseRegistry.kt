package com.example.domain.di

import com.example.domain.interactor.IBtUseCase
import com.example.domain.interactor.ICoreConnectionUseCase
import com.example.domain.interactor.IDeviceUseCase
import com.example.domain.interactor.IRuiUseCase
import com.example.domain.interactor.IWifiUseCase
import com.example.domain.usecase.BtConnectionUseCase
import com.example.domain.usecase.CoreConnectionUseCase
import com.example.domain.usecase.DeviceConnectionUseCase
import com.example.domain.usecase.RuiConnectionUseCase
import com.example.domain.usecase.WifiConnectionUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseRegistry {

    @[Binds Singleton]
     abstract fun bindsCoreUseCase(coreConnectionUseCase: CoreConnectionUseCase):ICoreConnectionUseCase

    @[Binds Singleton]
    abstract fun bindsBtUseCase(btConnectionUseCase: BtConnectionUseCase):IBtUseCase

    @[Binds Singleton]
    abstract fun bindsDeviceUsecase(deviceConnectionUseCase: DeviceConnectionUseCase):IDeviceUseCase

    @[Binds Singleton]
    abstract fun bindRuiUsecase(ruiConnectionUseCase: RuiConnectionUseCase):IRuiUseCase

    @[Binds Singleton]
    abstract fun bindWifiUsecase(wifiConnectionUseCase: WifiConnectionUseCase):IWifiUseCase

}