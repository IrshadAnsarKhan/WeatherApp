package com.capgemini.demo.weatherapp.view

import android.os.Bundle
import com.capgemini.demo.weatherapp.R
import com.capgemini.demo.weatherapp.base.BaseActivity
import com.capgemini.demo.weatherapp.home.HomeFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openHomeFragment()
    }

    private fun openHomeFragment() {
        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)
    }
}