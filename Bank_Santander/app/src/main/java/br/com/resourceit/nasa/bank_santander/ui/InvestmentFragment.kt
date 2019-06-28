package br.com.resourceit.nasa.bank_santander.ui


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.data.repository.FundRepository
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback
import kotlinx.android.synthetic.main.fragment_investment.*
import kotlinx.android.synthetic.main.fragment_investment.view.*
import java.util.*


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
        onRefresh()
        presenter()
    }

    private fun presenter() {
        FundRepository().getFundList(object : BaseCallback<ScreenModel> {
            override fun onSuccessful(value: ScreenModel) {

                recycler.apply {
                    adapter = AdapterMainScreen(value.info)
                }

                recyclerDownInfo.apply {
                    adapter = AdapterMainScreen(value.downInfo)
                }


                textViewFundInMonth.text = "${value.moreInfo.month.fund}"
                textViewFundInYear.text = "${value.moreInfo.year.fund}"
                textViewFundTwelveMonth.text = "${value.moreInfo.months.fund}"

                textViewCDIInMonth.text = "${value.moreInfo.month.cDI}"
                textViewCDIInYear.text = "${value.moreInfo.year.cDI}"
                textViewCDITwelveMonth.text = "${value.moreInfo.months.cDI}"

                textViewInfoTitle.text = value.infoTitle
                textViewRiskTitle.text = value.riskTitle
                textViewDefinition.text = value.definition
                textViewWhatIs.text = value.whatIs
                textViewFundName.text = value.fundName
                textViewTitle.text = value.title
                refresh.isRefreshing = false
                mainConstraint.visibility = View.VISIBLE
            }

            override fun onUnsuccessful(error: String) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                refresh.isRefreshing = false
            }

        })
    }

    private fun onRefresh() {
        refresh.isRefreshing = true
        refresh.setOnRefreshListener {
            mainConstraint.visibility = View.INVISIBLE
            presenter()
        }
    }

}
