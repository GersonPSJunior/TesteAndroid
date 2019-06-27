package br.com.resourceit.nasa.bank_santander


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class InvestmentFragment : Fragment() {
    lateinit var viewLayout:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        viewLayout = inflater.inflate(R.layout.fragment_investment, container, false)

        return view
    }


}
