package com.rodrigotristany.globallogic.ui.list.di

import com.rodrigotristany.globallogic.internal.di.components.ApplicationComponent
import com.rodrigotristany.globallogic.internal.di.scope.PerActivity
import com.rodrigotristany.globallogic.ui.list.LaptopsListActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [LaptopsListModule::class])
interface LaptopsListComponent {
    fun inject(laptopsListActivity: LaptopsListActivity)
}
