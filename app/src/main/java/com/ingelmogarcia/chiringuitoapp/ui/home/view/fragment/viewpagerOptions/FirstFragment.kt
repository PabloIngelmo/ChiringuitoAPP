package com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.viewpagerOptions

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ingelmogarcia.chiringuitoapp.R
import com.ingelmogarcia.chiringuitoapp.data.model.PedidoItemModel
import com.ingelmogarcia.chiringuitoapp.data.model.ProductoItemModel
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentFirstBinding
import com.ingelmogarcia.chiringuitoapp.ui.home.adapter.ProductoListAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel.HomeViewModel
import java.text.DecimalFormat


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var homeViewModel: HomeViewModel

    val database = Firebase.database
    val myRef = database.getReference("Productos").child("Cervezas")
    private lateinit var imageUrl :String
    private lateinit var name :String
    private lateinit var price :String
    var lista1: MutableList<ProductoItemModel> = mutableListOf()
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFirstBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        //ESCRITURA
        //myRef.child("caña_amstel").setValue("aaa")

        //LECTURA
        myRef.get().addOnSuccessListener {
            it.children.forEach {
                imageUrl = it.child("imageUrl").getValue().toString()
                name = it.child("name").getValue().toString()
                price = it.child("price").getValue().toString()
                lista1.add(ProductoItemModel(imageUrl, name, price.toDouble()))
            }
            binding.productoListaRecycler.layoutManager = GridLayoutManager(context,5)
            binding.productoListaRecycler.adapter = ProductoListAdapter(lista1,::onItemClicked)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
        return binding.root
    }

    private fun onItemClicked(productoItemModel: ProductoItemModel){
        var flag = false
        if(HomeViewModel.listaPedido.isEmpty()){
            HomeViewModel.listaPedido.add(PedidoItemModel(productoItemModel.name, 1,productoItemModel.price))
        }else {
            for (i in HomeViewModel.listaPedido) {
                if (i.productName.equals(productoItemModel.name)) {
                    flag = true
                    var priceReal = i.price/i.productQuantity
                    i.productQuantity++
                    i.price = priceReal * i.productQuantity
                }
            }
            if (flag == false){
                HomeViewModel.listaPedido.add(PedidoItemModel(productoItemModel.name, 1,productoItemModel.price))
            }
        }

        HomeViewModel.sumaTotal += productoItemModel.price
        homeViewModel._sumaTotalLD.postValue(HomeViewModel.sumaTotal)
        homeViewModel._botonVerPedidoLD.postValue(true)

        /*progressDialog = ProgressDialog(activity)
        progressDialog.show()
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.getWindow()!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(activity!!).get(HomeViewModel::class.java)
    }

}