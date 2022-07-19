package com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.ingelmogarcia.chiringuitoapp.R
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentHomeBinding
import com.ingelmogarcia.chiringuitoapp.ui.home.adapter.ViewPagerAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel.HomeViewModel
import com.ingelmogarcia.chiringuitoapp.ui.home.viewpagerOptions.FirstFragment
import java.math.RoundingMode
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    var df = DecimalFormat("#.##")
    //var ssssumaTotal: Double = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setUpViewPager()

        binding.botonVerPedido.setOnClickListener {

        }

        homeViewModel.sumaTotal.observe(this, Observer {
            binding.textviewTotal.text = "TOTAL: " + df.format(it) + "€"
        })


        return binding.root
    }


    fun setUpViewPager(){
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.setSaveEnabled(false)
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, index ->
            tab.text = when(index){
                0 -> { "Cerveza" }
                1 -> { "Resto bebidas" }
                2 -> { "Helados" }
                3 -> { "Comida" }
                else -> { throw Resources.NotFoundException(getString(R.string.notFoundException)) }
            }
        }.attach()
    }



}
