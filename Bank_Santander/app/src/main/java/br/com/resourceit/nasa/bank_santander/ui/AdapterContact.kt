package br.com.resourceit.nasa.bank_santander.ui

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.CellModel
import br.com.resourceit.nasa.bank_santander.data.remote.model.Type
import br.com.resourceit.nasa.bank_santander.data.remote.model.TypeField
import br.com.resourceit.nasa.bank_santander.utils.Validation
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.item_text_input_layout.view.*


class AdapterContact(private val context: Context, var list: List<CellModel>) :
    RecyclerView.Adapter<AdapterContact.MyViewHolder>() {

    var itemsList: MutableMap<Int, View> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return when (list[viewType].type) {
            Type.field.index -> {
                MyViewHolder(
                    configureField(
                        list[viewType],
                        viewType,
                        LayoutInflater.from(context).inflate(
                            R.layout.item_text_input_layout,
                            parent,
                            false
                        ) as ViewGroup
                    )
                )
            }
            Type.text.index -> {
                MyViewHolder(
                    configureTextView(
                        list[viewType], LayoutInflater.from(context)
                            .inflate(R.layout.item_textview, parent, false) as TextView
                    )
                )
            }
            Type.image.index -> MyViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.item_text_input_layout, parent, false)
            )
            Type.checkbox.index -> {
                MyViewHolder(
                    configureCheckbox(
                        list[viewType], LayoutInflater.from(context)
                            .inflate(R.layout.item_checkbox, parent, false) as CheckBox
                    )
                )
            }
            Type.send.index -> {
                MyViewHolder(
                    configureButton(
                        list[viewType], LayoutInflater.from(context)
                            .inflate(R.layout.item_button, parent, false) as Button
                    )
                )
            }
            else -> {
                MyViewHolder(
                    configureField(
                        list[viewType], viewType, LayoutInflater.from(context)
                            .inflate(R.layout.item_text_input_layout, parent, false) as TextInputLayout
                    )
                )
            }
        }


    }

    private fun configureField(cellModel: CellModel, position: Int, itemView: ViewGroup): View {
        val child = itemView.getChildAt(0) as TextInputLayout
        child.hint = cellModel.message
        child.id = cellModel.id
        child.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 0, child)
        if (cellModel.typefield is Double)
            cellModel.typefield = (cellModel.typefield as Double).toInt()
        when (cellModel.typefield) {
            TypeField.text.index -> {
                child.editText?.setOnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus)
                        if (Validation.requiredField(child.editText!!.text.toString())) {
                            child.error = " "
                            return@setOnFocusChangeListener
                        } else child.isErrorEnabled = false

                }

            }
            TypeField.email.index -> {
                child.editText?.setOnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus)
                        if (!Validation.emailValidation(child.editText!!.text.toString())) {
                            child.error = " "
                            return@setOnFocusChangeListener
                        } else child.isErrorEnabled = false

                }
            }
            TypeField.telnumber.name -> {

                child.editText?.let {
                    it.inputType = InputType.TYPE_CLASS_NUMBER
                    Validation.insert(it)
                }

            }
        }

        itemsList[cellModel.id] = child
        showVisible(cellModel, !cellModel.hidden, cellModel.id)
        return itemView
    }

    private fun configureMargin(top: Int, bottom: Int, itemView: View): ViewGroup.MarginLayoutParams {
        val p = itemView.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(0, top, 0, bottom)
        return p
    }

    private fun configureCheckbox(cellModel: CellModel, itemView: CheckBox): View {
        itemView.text = cellModel.message
        itemView.id = cellModel.id
        itemView.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 0, itemView)
        itemView.setOnClickListener {
            if (itemView.isChecked)
                showVisible(cellModel, true, cellModel.show)
            else
                showVisible(cellModel, false, cellModel.show)
        }

        itemsList[cellModel.id] = itemView
        return itemView
    }

    private fun showVisible(cellModel: CellModel, visibility: Boolean, show: Int) {
        if (cellModel.show == null) return
        itemsList[show]?.visibility = if (visibility) View.VISIBLE else View.GONE
    }


    private fun configureButton(cellModel: CellModel, itemView: Button): View {
        itemView.text = cellModel.message
        itemView.id = cellModel.id
        itemView.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 86, itemView)

        itemView.setOnClickListener {
            for (cell in list) {
                for (item in itemsList) {
                    if (item.key == cell.id) {
                        if (cell.type == Type.field.index) {
                            //if(item.value.textInputEditText.text != null)
                            Toast.makeText(
                                context,
                                " Clickou ${item.value.textInputEditText.text.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
//                        else if(cell.type == Type.checkbox.index){
//                            Toast.makeText(context, "Checkbox validado ${item.value.itemCheckBox.isChecked}", Toast.LENGTH_SHORT ).show()
//                        }
                    }
                }
            }

        }
        itemsList[cellModel.id] = itemView
        return itemView
    }

    private fun configureTextView(cellModel: CellModel, itemView: TextView): View {
        itemView.text = cellModel.message
        itemView.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 0, itemView)
        return itemView
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = if (list.isEmpty()) 0 else list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    data class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }

    }
}