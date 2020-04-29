package com.rodrigotristany.globallogic.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Laptop(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("image")
    @Expose
    val image: String
)
