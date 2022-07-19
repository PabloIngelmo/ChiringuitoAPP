package com.ingelmogarcia.chiringuitoapp.ui.home.viewpagerOptions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ingelmogarcia.chiringuitoapp.data.model.ProductoItemModel
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentFirstBinding
import com.ingelmogarcia.chiringuitoapp.ui.home.adapter.ProductoListAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.HomeFragment
import com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel.HomeViewModel


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    val database = Firebase.database
    val myRef = database.getReference("Productos").child("Cervezas")
    var homeFragment :HomeFragment = HomeFragment()
    var homeViewModel :HomeViewModel = HomeViewModel()
    private lateinit var imageUrl :String
    private lateinit var name :String
    private lateinit var price :String
    var lista1: MutableList<ProductoItemModel> = mutableListOf()
    var ssssumaTotal: Double = 0.0

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
        ssssumaTotal += productoItemModel.price
        homeViewModel.aa(ssssumaTotal)
    }

}