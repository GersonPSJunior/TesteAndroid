package br.com.resourceit.nasa.bank_santander.ui.contact


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.CellModel
import br.com.resourceit.nasa.bank_santander.ui.AdapterContact
import br.com.resourceit.nasa.bank_santander.ui.BankContract
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment(var viewHome: BankContract.View) : Fragment(), ContactContract.View {


    lateinit var presenter : ContactContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ContactPresenter(this)
        presenter.loadView()
        textViewBackToForm.setOnClickListener {
            viewHome.showFragment(ContactFragment(viewHome))
        }
    }

    override fun showRecycler(cells: List<CellModel>) {
        recyclerContact.apply {

            adapter = AdapterContact(context, cells, this@ContactFragment)
        }
    }

    override fun notification(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun enabledRecycler(key: Boolean) {
        recyclerContact.visibility = if(key) View.VISIBLE else View.INVISIBLE
        constraintMessage.visibility = if(key) View.INVISIBLE else View.VISIBLE
    }

}
