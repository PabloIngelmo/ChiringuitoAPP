package com.ingelmogarcia.chiringuitoapp.ui.home.viewpagerOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ingelmogarcia.chiringuitoapp.R
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentFirstBinding
import com.ingelmogarcia.chiringuitoapp.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentThirdBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        return binding.root
    }


}