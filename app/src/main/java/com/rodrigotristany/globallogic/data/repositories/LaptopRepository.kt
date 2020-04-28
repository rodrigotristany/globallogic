package com.rodrigotristany.globallogic.data.repositories

import com.rodrigotristany.globallogic.data.api.GlobalLogicApi
import javax.inject.Inject

class LaptopRepository @Inject constructor(private val globalLogicApi: GlobalLogicApi){
    fun laptops() = globalLogicApi.getLaptops()
}
