package br.com.resourceit.nasa.bank_santander.ui

import android.content.Context
import android.view.MenuItem
import androidx.fragment.app.Fragment

class BankContract {

    interface View{
        fun showFragment(fragment: Fragment)
        fun enableNavigation(result : Boolean)
        fun getContext(): Context

    }
    interface Presenter{
        fun identifyItemClicked(menuItem: MenuItem)
    }
}