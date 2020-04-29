package com.rodrigotristany.globallogic

import com.rodrigotristany.globallogic.data.models.Laptop
import com.rodrigotristany.globallogic.data.repositories.LaptopRepository
import com.rodrigotristany.globallogic.domain.GetLaptopsUseCase
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(LaptopRepository::class)
class GetLaptopsUseCaseUnitTest {
    private lateinit var getLaptopsUseCase: GetLaptopsUseCase

    @Mock
    lateinit var laptopRepository: LaptopRepository

    val laptops = listOf(
        Laptop(
            title = "TestLaptop 1 - Linux",
            image = "https://picsum.photos/500/500?image=4",
            description = "Esto es unit test de laptop 1 - linux"),
        Laptop(
            title = "TestLaptop 2 - Windows",
            image = "https://picsum.photos/500/500?image=4",
            description = "Esto es unit test de laptop 2 - Windows"),
        Laptop(
            title = "TestLaptop 3 - Apple",
            image = "https://picsum.photos/500/500?image=4",
            description = "Esto es unit test de laptop 3 - Apple")
    )

    @Before
    fun setUp() {
        Mockito.`when`(laptopRepository.laptops())
            .thenReturn(Observable.just(laptops))

        getLaptopsUseCase = GetLaptopsUseCase(laptopRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @After
    fun tearDown() {
        getLaptopsUseCase.dispose()
    }

    @Test
    fun multipleTestImplementation() {
        val observer = TestObserver<List<Laptop>>()
        getLaptopsUseCase.execute(observer)
        observer.assertNoErrors()
        observer.assertComplete()
        assertEquals("TestLaptop 1 - Linux", observer.values()[0][0].title)
        assertEquals("Esto es unit test de laptop 3 - Apple", observer.values()[0][2].description)
        assertEquals(3, observer.values()[0].size)
    }
}
