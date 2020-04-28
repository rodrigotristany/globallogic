package com.rodrigotristany.globallogic.internal

import android.app.Application
import com.rodrigotristany.globallogic.internal.di.components.ApplicationComponent
import com.rodrigotristany.globallogic.internal.di.components.DaggerApplicationComponent
import com.rodrigotristany.globallogic.internal.di.modules.ApplicationModule

class App: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        applicationComponent.inject(this)
    }
}
