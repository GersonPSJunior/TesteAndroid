package br.com.resourceit.nasa.bank_santander.ui.dashboard

import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel

class InvestmentContract {
    interface ViewInvestment {
        fun loadList(value: ScreenModel)
        fun showProgress(key: Boolean)
        fun showMessage(text: String)

    }

    interface PresenterInvestment {
        fun getFundList()
    }
}