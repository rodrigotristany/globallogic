package com.rodrigotristany.globallogic.internal.di.modules

import com.rodrigotristany.globallogic.data.repositories.LaptopRepository
import com.rodrigotristany.globallogic.domain.GetLaptopsUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    internal fun provideGetLaptopsUseCase(
        laptopRepository: LaptopRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): GetLaptopsUseCase =
        GetLaptopsUseCase(laptopRepository, ioScheduler, mainThreadScheduler)

}
