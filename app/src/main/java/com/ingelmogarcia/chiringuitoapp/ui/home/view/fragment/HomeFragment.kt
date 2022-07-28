package com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.ingelmogarcia.chiringuitoapp.R
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentHomeBinding
import com.ingelmogarcia.chiringuitoapp.ui.home.adapter.ViewPagerAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.components.VerPedidoDialog
import com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel.HomeViewModel
import java.text.DecimalFormat
import android.app.FragmentManager;

class HomeFragment : Fragment() {

    private val TAG_DIALOG = "dialog"

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    var df = DecimalFormat("#.##")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setUpViewPager()


        binding.botonVerPedido.setOnClickListener {
            VerPedidoDialog().show(activity!!.supportFragmentManager,TAG_DIALOG)
        }

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(activity!!).get(HomeViewModel::class.java)

        homeViewModel._sumaTotalLD.observe(viewLifecycleOwner, Observer {
            binding.textviewTotal.text = "TOTAL: " + df.format(it) + "€"
        })

        homeViewModel._botonVerPedidoLD.observe(viewLifecycleOwner, Observer {
            binding.botonVerPedido.isEnabled = it
        })
    }

}
