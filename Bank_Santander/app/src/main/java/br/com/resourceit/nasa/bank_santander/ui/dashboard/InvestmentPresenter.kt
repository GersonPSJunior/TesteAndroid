package br.com.resourceit.nasa.bank_santander.ui.dashboard

import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.data.repository.FundRepository
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback

class InvestmentPresenter(val view: InvestmentContract.ViewInvestment) : InvestmentContract.PresenterInvestment {

    override fun getFundList() {

        view.showProgress(true)
        FundRepository().getFundList(object : BaseCallback<ScreenModel> {

            override fun onSuccessful(value: ScreenModel) {
                view.loadList(value)
                view.showProgress(false)
            }

            override fun onUnsuccessful(error: String) {
                view.showMessage(error)
                view.showProgress(false)
            }

        })
    }
}

