package br.com.resourceit.nasa.bank_santander.data.remote.model


data class CellModel(
    val id: Int,
    val type: Int,
    val message: String,
    val typefield: Any,
    val hidden: Boolean,
    val topSpacing: Double,
    val show: Any,
    val required: Boolean
)