package br.com.resourceit.nasa.bank_santander.ui.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.ui.BankContract
import kotlinx.android.synthetic.main.fragment_investment.*
import kotlinx.android.synthetic.main.risk_bar_layout.*
import java.util.*


class InvestmentFragment(var viewHome : BankContract.View) : Fragment(), InvestmentContract.ViewInvestment {


    val random = Random()
    lateinit var presenter: InvestmentContract.PresenterInvestment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_investment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = InvestmentPresenter(this)
        presenter.getFundList()
        onRefresh()
    }


    private fun onRefresh() {
        refresh.setOnRefreshListener {
            presenter.getFundList()
        }
    }


    private fun setRiskBar(level: Int) {

        when (level) {
            1 -> {
                RiskBarLightGreen.visibility = View.GONE
                SelectedRiskBarLightGreen.visibility = View.VISIBLE
                imageArrowLightGreen.visibility = View.VISIBLE
            }

            2 -> {
                RiskBarGreen.visibility = View.GONE
                SelectedRiskBarGreen.visibility = View.VISIBLE
                imageArrowGreen.visibility = View.VISIBLE
            }

            3 -> {
                RiskBarYellow.visibility = View.GONE
                SelectedRiskBarYellow.visibility = View.VISIBLE
                imageArrowYellow.visibility = View.VISIBLE
            }

            4 -> {
                RiskBarOrange.visibility = View.GONE
                SelectedRiskBarOrange.visibility = View.VISIBLE
                imageArrowOrange.visibility = View.VISIBLE
            }

            5 -> {
                RiskBarRed.visibility = View.GONE
                SelectedRiskBarRed.visibility = View.VISIBLE
                imageArrowRed.visibility = View.VISIBLE
            }
        }


    }

    private fun hideAllSelectedRiskBar() {
        SelectedRiskBarRed.visibility = View.GONE
        imageArrowRed.visibility = View.INVISIBLE
        RiskBarRed.visibility = View.VISIBLE

        SelectedRiskBarOrange.visibility = View.GONE
        imageArrowOrange.visibility = View.INVISIBLE
        RiskBarOrange.visibility = View.VISIBLE

        SelectedRiskBarYellow.visibility = View.GONE
        imageArrowYellow.visibility = View.INVISIBLE
        RiskBarYellow.visibility = View.VISIBLE

        SelectedRiskBarGreen.visibility = View.GONE
        imageArrowGreen.visibility = View.INVISIBLE
        RiskBarGreen.visibility = View.VISIBLE

        SelectedRiskBarLightGreen.visibility = View.GONE
        imageArrowLightGreen.visibility = View.INVISIBLE
        RiskBarLightGreen.visibility = View.VISIBLE
    }

    fun rand(): Int {
        return random.nextInt(7 - 2) + 1 // from(incluso) e to(excluso)
    }

    override fun loadList(value: ScreenModel) {
        hideAllSelectedRiskBar()

        recycler.apply {
            adapter = AdapterMainScreen(value.info)
        }

        recyclerDownInfo.apply {
            adapter = AdapterMainScreen(value.downInfo)
        }

        setTextValues(value)
    }

    private fun setTextValues(value: ScreenModel) {
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
        //setRiskBar(value.risk)
        setRiskBar(rand())
    }

    override fun showProgress(key: Boolean) {
        refresh.isRefreshing = key
        mainConstraint.visibility = if (key) View.INVISIBLE else View.VISIBLE
        viewHome.enableNavigation(!key)

    }

    override fun showMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

}
