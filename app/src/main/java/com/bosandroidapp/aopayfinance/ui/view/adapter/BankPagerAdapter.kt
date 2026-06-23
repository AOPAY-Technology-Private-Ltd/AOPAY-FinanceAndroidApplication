package com.bosandroidapp.aopayfinance.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bosandroidapp.aopayfinance.ui.view.activity.fragment.AddBank
import com.bosandroidapp.aopayfinance.ui.view.activity.fragment.BankListPage
import com.bosandroidapp.aopayfinance.ui.view.activity.fragment.PayoutPage
import com.bosandroidapp.aopayfinance.ui.view.activity.fragment.PayoutReports
private const val NUM_TABS = 2
class BankPagerAdapter (fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {


    override fun getItemCount(): Int {
        return NUM_TABS
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BankListPage()
            1-> AddBank()
            else -> BankListPage()
        }

    }

}