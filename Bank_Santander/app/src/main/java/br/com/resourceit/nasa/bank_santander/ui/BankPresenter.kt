package br.com.resourceit.nasa.bank_santander.ui

import android.view.MenuItem
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.ui.contact.ContactFragment
import br.com.resourceit.nasa.bank_santander.ui.dashboard.InvestmentFragment

class BankPresenter(val view : BankContract.View) : BankContract.Presenter {
    override fun identifyItemClicked(menuItem: MenuItem) {
        when(menuItem.itemId){
            R.id.menuInvestment -> view.showFragment(InvestmentFragment(view))
            R.id.menuContact -> view.showFragment(ContactFragment(view))
        }
    }

}