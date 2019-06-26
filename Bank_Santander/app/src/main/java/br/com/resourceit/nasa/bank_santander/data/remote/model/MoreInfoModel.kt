package br.com.resourceit.nasa.bank_santander.data.remote.model


import com.google.gson.annotations.SerializedName

data class MoreInfoModel(
    val month: DateModel,
    val year: DateModel,
    @SerializedName("12months")
    val months: DateModel
)