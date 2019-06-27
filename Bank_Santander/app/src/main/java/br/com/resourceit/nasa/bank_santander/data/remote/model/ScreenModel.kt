package br.com.resourceit.nasa.bank_santander.data.remote.model


data class ScreenModel(
    val title: String,
    val fundName: String,
    val whatIs: String,
    val definition: String,
    val riskTitle: String,
    val risk: Int,
    val infoTitle: String,
    val moreInfo: MoreInfoModel,
    val info: List<InfoModel>,
    val downInfo: List<InfoModel>
)