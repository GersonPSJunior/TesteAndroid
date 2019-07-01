package br.com.resourceit.nasa.bank_santander.ui

import android.view.MenuItem
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.ui.contact.ContactFragment

class BankPresenter(val view : BankContract.View) : BankContract.Presenter {
    override fun identifyItemClicked(menuItem: MenuItem) {
        when(menuItem.itemId){
            R.id.menuInvestment -> view.showFragment(InvestmentFragment())
            R.id.menuContact -> view.showFragment(ContactFragment())
        }
    }

}