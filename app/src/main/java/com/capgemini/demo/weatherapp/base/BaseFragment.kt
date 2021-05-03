package com.capgemini.demo.weatherapp.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun onFragmentBackPressed() {
        requireActivity().onBackPressed()
    }
}