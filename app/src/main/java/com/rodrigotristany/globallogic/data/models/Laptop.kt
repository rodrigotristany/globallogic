package com.rodrigotristany.globallogic.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Laptop(
    @SerializedName("geonames")
    @Expose
    val title: String,

    @SerializedName("status")
    @Expose
    val description: String,

    @SerializedName("status")
    @Expose
    val image: String
)
