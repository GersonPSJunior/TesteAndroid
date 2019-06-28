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
        onRefresh(view)
        presenter(view)
    }

    private fun presenter(view:View) {
        FundRepository().getFundList(object : BaseCallback<ScreenModel> {
            override fun onSuccessful(value: ScreenModel) {

                view.recycler.apply {
                    adapter = AdapterMainScreen(value.info)
                }

                view.recyclerDownInfo.apply {
                    adapter = AdapterMainScreen(value.downInfo)
                }


                view.textViewFundInMonth.text = "${value.moreInfo.month.fund}"
                view.textViewFundInYear.text = "${value.moreInfo.year.fund}"
                view.textViewFundTwelveMonth.text = "${value.moreInfo.months.fund}"

                view.textViewCDIInMonth.text = "${value.moreInfo.month.cDI}"
                view.textViewCDIInYear.text = "${value.moreInfo.year.cDI}"
                view.textViewCDITwelveMonth.text = "${value.moreInfo.months.cDI}"

                view.textViewInfoTitle.text = value.infoTitle
                view.textViewRiskTitle.text = value.riskTitle
                view.textViewDefinition.text = value.definition
                view.textViewWhatIs.text = value.whatIs
                view.textViewFundName.text = value.fundName
                view.textViewTitle.text = value.title
                view.refresh.isRefreshing = false
                view.mainConstraint.visibility = View.VISIBLE
            }

            override fun onUnsuccessful(error: String) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                view.refresh.isRefreshing = false
            }

        })
    }

    private fun onRefresh(view:View) {
        refresh.isRefreshing = true
        refresh.setOnRefreshListener {
            mainConstraint.visibility = View.INVISIBLE
            presenter(view)
        }
    }

}
