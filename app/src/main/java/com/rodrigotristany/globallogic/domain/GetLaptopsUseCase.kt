package com.rodrigotristany.globallogic.domain

import com.rodrigotristany.globallogic.data.models.Laptop
import com.rodrigotristany.globallogic.data.repositories.LaptopRepository
import com.rodrigotristany.globallogic.domain.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetLaptopsUseCase
@Inject constructor(private val laptopRepository: LaptopRepository,
                    subscribeScheduler: Scheduler,
                    postExecutionScheduler: Scheduler
) : UseCase<List<Laptop>, Unit>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: Unit?): Observable<List<Laptop>> = laptopRepository.laptops()
}
