package br.com.resourceit.nasa.bank_santander.ui.dashboard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.resourceit.nasa.bank_santander.R
import br.com.resourceit.nasa.bank_santander.data.remote.model.InfoModel
import kotlinx.android.extensions.LayoutContainer

import kotlinx.android.synthetic.main.card_main_screen.*


class AdapterMainScreen(var list: List<InfoModel>) : RecyclerView.Adapter<AdapterMainScreen.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemList: View = LayoutInflater.from(parent.context).inflate(R.layout.card_main_screen, parent, false)

        return MyViewHolder(itemList, itemList)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model: InfoModel = list.get(position)
        holder.textName.text = model.name
        if (model.data == null) {
            holder.textData.text = "Baixar"
            holder.textData.setTextColor(Color.parseColor("#da0101"))
            holder.setVisibleImage()
        } else {
            holder.textData.text = model.data

        }
    }

    data class MyViewHolder(val itemView: View, override val containerView: View?) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {

        var textName: TextView = textViewInfoName
        var textData: TextView = textViewInfoData

        fun setVisibleImage() {
            imageViewArrow.visibility = View.VISIBLE
            imageViewUnder.visibility = View.VISIBLE
        }
    }


}