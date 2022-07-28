package com.ingelmogarcia.chiringuitoapp.ui.home.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ingelmogarcia.chiringuitoapp.R
import com.ingelmogarcia.chiringuitoapp.data.model.PedidoItemModel
import com.ingelmogarcia.chiringuitoapp.data.model.ProductoItemModel
import java.text.DecimalFormat

class PedidoListAdapter(private var listaPedidos :List<PedidoItemModel>,
                        private val listener: (PedidoItemModel) -> Unit) : RecyclerView.Adapter<PedidoListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_pedido, parent, false)

        return PedidoListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaPedidos[position])
    }

    override fun getItemCount() :Int = listaPedidos.size

    class ViewHolder(view : View) :RecyclerView.ViewHolder(view){
        private val productName = view.findViewById<TextView>(R.id.productName)
        private val productQuantity = view.findViewById<TextView>(R.id.productQuantity)
        private val price = view.findViewById<TextView>(R.id.price)
        var btnExpandir = view.findViewById<Button>(R.id.buttonExpandirItem)
        var expandirView = view.findViewById<View>(R.id.expandirView)
        var df = DecimalFormat("#.##")

        fun bind(pedidoItemModel: PedidoItemModel) {
            this.productName.text = pedidoItemModel.productName
            this.productQuantity.text = pedidoItemModel.productQuantity.toString()
            this.price.text = df.format(pedidoItemModel.price) + "€"

            btnExpandir.setOnClickListener {
                expandirView.visibility = View.VISIBLE
            }
        }

    }
}