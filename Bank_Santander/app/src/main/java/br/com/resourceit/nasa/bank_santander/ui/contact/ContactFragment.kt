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
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment(), ContactContract.View {

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
    }

    override fun showRecycler(cells: List<CellModel>) {
        recyclerContact.apply {

            adapter = AdapterContact(context, cells)
        }
    }

    override fun notification(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}
