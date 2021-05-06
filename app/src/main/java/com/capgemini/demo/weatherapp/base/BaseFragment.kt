package com.capgemini.demo.weatherapp.base

import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    protected fun onFragmentBackPressed() {
        requireActivity().onBackPressed()
    }
}