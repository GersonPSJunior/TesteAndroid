package br.com.resourceit.nasa.bank_santander.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.core.view.size
import androidx.fragment.app.Fragment
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.data.repository.FundRepository
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BankContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomNavigationConfig.adjustGravity(bottomNavigation)

        val presenter : BankContract.Presenter = BankPresenter(this)

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            presenter.identifyItemClicked(menuItem)
            return@setOnNavigationItemSelectedListener true
        }

    }
    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameBank, fragment).commit()
    }
    override fun enableNavigation(result: Boolean) {
        for(i in 0..bottomNavigation.size) bottomNavigation.menu.getItem(i).isEnabled = result
    }

    override fun getContext(): Context = this

}
