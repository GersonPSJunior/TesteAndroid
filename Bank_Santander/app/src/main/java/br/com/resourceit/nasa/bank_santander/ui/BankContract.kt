package br.com.resourceit.nasa.bank_santander.ui

import android.view.MenuItem
import androidx.fragment.app.Fragment

class BankContract {

    interface View{
        fun showFragment(fragment: Fragment)
        fun enableNavigation(result : Boolean)

    }
    interface Presenter{
        fun identifyItemClicked(menuItem: MenuItem)
    }
}