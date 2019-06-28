package br.com.resourceit.nasa.bank_santander.ui


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.data.repository.FundRepository
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback
import kotlinx.android.synthetic.main.fragment_investment.*


class InvestmentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_investment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FundRepository().getFundList(object : BaseCallback<ScreenModel> {
            override fun onSuccessful(value: ScreenModel) {

                recycler.apply {
                    adapter = AdapterMainScreen(value.info)
                }

                recyclerDownInfo.apply {
                    adapter = AdapterMainScreen(value.downInfo)
                }
            }

            override fun onUnsuccessful(error: String) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }

        })
    }

}
