package com.ingelmogarcia.chiringuitoapp.ui.home.components

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ingelmogarcia.chiringuitoapp.data.model.PedidoItemModel
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentVerPedidoDialogBinding
import com.ingelmogarcia.chiringuitoapp.ui.home.adapter.PedidoListAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel.HomeViewModel
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.HashSet


class VerPedidoDialog : DialogFragment(){

    private lateinit var binding : FragmentVerPedidoDialogBinding

    var sumaPedido :Double = 0.0
    var df = DecimalFormat("#.##")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentVerPedidoDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)


        binding.pedidoListaRecycler.layoutManager = LinearLayoutManager(context)
        binding.pedidoListaRecycler.adapter = PedidoListAdapter(HomeViewModel.listaPedido, ::onItemClicked)

        for (i in HomeViewModel.listaPedido){
            sumaPedido += i.price
        }
        binding.totalPedidoTV.text = df.format(sumaPedido) + "€"


        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private fun onItemClicked(pedidoItemModel: PedidoItemModel){
        Toast.makeText(activity, "Notificación corta", Toast.LENGTH_SHORT).show()
    }
}