package br.com.resourceit.nasa.bank_santander.ui.contact

import br.com.resourceit.nasa.bank_santander.data.remote.model.CellModel
import br.com.resourceit.nasa.bank_santander.data.repository.CellRepository
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback

class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {

    override fun loadView() {
        CellRepository().getCellsList(object : BaseCallback<List<CellModel>> {
            override fun onSuccessful(value: List<CellModel>) {
                view.showRecycler(value)
            }

            override fun onUnsuccessful(error: String) {
                view.notification(error)
            }

        })
    }


}