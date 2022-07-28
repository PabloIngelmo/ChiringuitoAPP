package com.ingelmogarcia.chiringuitoapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ingelmogarcia.chiringuitoapp.R
import com.ingelmogarcia.chiringuitoapp.data.model.ProductoItemModel


class ProductoListAdapter(
    private val listaProductos: List<ProductoItemModel>,
    private val listener: (ProductoItemModel) -> Unit
) : RecyclerView.Adapter<ProductoListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_producto, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaProductos[position])
        holder.itemView.setOnClickListener { listener(listaProductos[position]) }
    }

    override fun getItemCount(): Int = listaProductos.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.image)
        private val title = view.findViewById<TextView>(R.id.title)

        fun bind(productoItemModel: ProductoItemModel) {
            Glide
                .with(image.context)
                .load(productoItemModel.urlImage)
                .into(image)
            this.title.text = productoItemModel.name + " [" + productoItemModel.price +"€]"
        }
    }
}