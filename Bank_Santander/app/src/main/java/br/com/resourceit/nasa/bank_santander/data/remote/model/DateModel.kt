package br.com.resourceit.nasa.bank_santander.data.remote.model


import com.google.gson.annotations.SerializedName

data class DateModel(
    val fund: Double,
    @SerializedName("CDI")
    val cDI: Double
)