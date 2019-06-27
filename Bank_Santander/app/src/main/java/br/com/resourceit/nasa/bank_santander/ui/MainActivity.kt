package br.com.resourceit.nasa.bank_santander.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.data.repository.FundRepository
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FundRepository().getFundList(object : BaseCallback<ScreenModel>{
            override fun onSuccessful(value: ScreenModel) {
                recycler.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = AdapterMainScreen(value.info)
                }

                recyclerDownInfo.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = AdapterMainScreen(value.downInfo)
                }
            }

            override fun onUnsuccessful(error: String) {
            }

        })



    }
}
