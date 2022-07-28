package com.ingelmogarcia.chiringuitoapp.ui.home.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.viewpagerOptions.FirstFragment
import com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.viewpagerOptions.FourthFragment
import com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.viewpagerOptions.SecondFragment
import com.ingelmogarcia.chiringuitoapp.ui.home.view.fragment.viewpagerOptions.ThirdFragment

class ViewPagerAdapter(manager: FragmentActivity): FragmentStateAdapter(manager) {

    val POSITION_NOT_FOUND = "Position Not Found"

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { FirstFragment() }
            1 -> { SecondFragment() }
            2 -> { ThirdFragment() }
            3 -> { FourthFragment() }
            else -> { throw Resources.NotFoundException(POSITION_NOT_FOUND) }
        }
    }
}