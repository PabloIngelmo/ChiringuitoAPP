package com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.viewpagerOptions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ingelmogarcia.chiringuitoapp.data.model.ProductoItemModel
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentSecondBinding
import com.ingelmogarcia.chiringuitoapp.ui.home.adapter.ProductoListAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel.HomeViewModel


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var homeViewModel: HomeViewModel

    val database = Firebase.database
    val myRef = database.getReference("Productos").child("Cervezas")
    private lateinit var imageUrl :String
    private lateinit var name :String
    private lateinit var price :String
    var lista1: MutableList<ProductoItemModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)
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
            binding.productoListaRecycler2.layoutManager = GridLayoutManager(context,5)
            binding.productoListaRecycler2.adapter = ProductoListAdapter(lista1,::onItemClicked)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        return binding.root
    }

    private fun onItemClicked(productoItemModel: ProductoItemModel){
        HomeViewModel.sumaTotal += productoItemModel.price
        homeViewModel._sumaTotalLD.postValue(HomeViewModel.sumaTotal)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(activity!!).get(HomeViewModel::class.java)
    }

}