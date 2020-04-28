package com.rodrigotristany.globallogic.ui.list.di

import android.content.Context
import com.rodrigotristany.globallogic.domain.GetLaptopsUseCase
import com.rodrigotristany.globallogic.internal.di.scope.PerActivity
import com.rodrigotristany.globallogic.ui.list.LaptopsListMVP
import com.rodrigotristany.globallogic.ui.list.LaptopsListPresenter
import dagger.Module
import dagger.Provides

@Module
class LaptopsListModule {
    @PerActivity
    @Provides
    fun provideLaptopsListPresenter(getLaptopsUseCase: GetLaptopsUseCase,
                                    context: Context) : LaptopsListMVP.Presenter {
        return LaptopsListPresenter(getLaptopsUseCase, context)
    }
}
