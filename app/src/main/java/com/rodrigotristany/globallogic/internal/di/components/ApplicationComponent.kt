package com.rodrigotristany.globallogic.internal.di.components

import android.content.Context
import com.rodrigotristany.globallogic.data.api.GlobalLogicApi
import com.rodrigotristany.globallogic.domain.GetLaptopsUseCase
import com.rodrigotristany.globallogic.internal.App
import com.rodrigotristany.globallogic.internal.di.modules.ApplicationModule
import com.rodrigotristany.globallogic.internal.di.modules.DataModule
import com.rodrigotristany.globallogic.internal.di.modules.DomainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DomainModule::class,
    DataModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun applicationContext(): Context
    fun globalLogicApi(): GlobalLogicApi
    fun getLaptopsUseCase(): GetLaptopsUseCase
}
