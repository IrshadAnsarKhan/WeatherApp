package com.capgemini.demo.weatherapp.base

import android.os.Bundle
import android.view.View
import com.capgemini.demo.weatherapp.view.MainActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    protected fun onFragmentBackPressed() {
        requireActivity().onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).setStatusBarGreen()

        try {
            (requireActivity() as MainActivity).supportActionBar!!.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}