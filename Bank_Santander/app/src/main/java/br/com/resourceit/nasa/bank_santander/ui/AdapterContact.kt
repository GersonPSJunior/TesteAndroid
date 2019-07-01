package br.com.resourceit.nasa.bank_santander.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import br.com.resourceit.nasa.bank_santander.data.remote.model.CellModel
import br.com.resourceit.nasa.bank_santander.data.remote.model.Type
import com.google.android.material.textfield.TextInputLayout
import br.com.resourceit.nasa.bank_santander.R


class AdapterContact(private val context: Context, var list: List<CellModel>) : RecyclerView.Adapter<AdapterContact.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return when(list[viewType].type){
            Type.field.index -> {
                MyViewHolder(configureField(list[viewType], LayoutInflater.from(context)
                    .inflate(R.layout.item_text_input_layout, parent, false) as TextInputLayout))
            }
            Type.text.index -> MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_text_input_layout, parent, false))
            Type.image.index -> MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_text_input_layout, parent, false))
            Type.checkbox.index -> {
                MyViewHolder(configureCheckbox(list[viewType], LayoutInflater.from(context)
                    .inflate(R.layout.item_checkbox, parent, false) as CheckBox))
            }
            Type.send.index -> {
                MyViewHolder(configureButton(list[viewType], LayoutInflater.from(context)
                    .inflate(R.layout.item_button, parent, false) as Button))
            }
            else -> {
                MyViewHolder(configureField(list[viewType], LayoutInflater.from(context)
                    .inflate(R.layout.item_text_input_layout, parent, false) as TextInputLayout))
            }
        }


    }

    private fun configureField(cellModel: CellModel, itemView: TextInputLayout): View {
        itemView.hint = cellModel.message
        itemView.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 0, itemView)

        return itemView
    }

    private fun configureMargin(top: Int, bottom: Int, itemView: View): ViewGroup.MarginLayoutParams {
        val p = itemView.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(0, top, 0, bottom)
        return p
    }

    private fun configureCheckbox(cellModel: CellModel, itemView: CheckBox):View {
        itemView.text = cellModel.message
        itemView.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 0, itemView)
        return itemView
    }

    private fun configureButton(cellModel: CellModel, itemView:Button):View{
        itemView.text = cellModel.message
        itemView.layoutParams = configureMargin(cellModel.topSpacing.toInt(), 86, itemView)
        return itemView
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = if(list.isEmpty()) 0 else list.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    data class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}