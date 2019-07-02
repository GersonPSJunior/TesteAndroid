package br.com.resourceit.nasa.bank_santander.data.remote.model


data class CellModel(
    val id: Int,
    val type: Int,
    val message: String,
    var typefield: Any,
    var hidden: Boolean,
    val topSpacing: Double,
    val show: Int,
    val required: Boolean
)