package com.rodrigotristany.globallogic.data.api

import com.rodrigotristany.globallogic.data.models.Laptop
import io.reactivex.Observable
import retrofit2.http.GET

interface GlobalLogicApi {
    @GET("list")
    fun getLaptops(): Observable<List<Laptop>>
}
