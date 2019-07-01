package br.com.resourceit.nasa.bank_santander.ui.contact

import br.com.resourceit.nasa.bank_santander.data.remote.model.CellModel

class ContactContract {

    interface View{
        fun showRecycler(cells:List<CellModel>)

        fun notification(error: String)
    }
    interface Presenter{
        fun loadView()

    }
}