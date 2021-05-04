package com.capgemini.demo.weatherapp.base


import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capgemini.demo.weatherapp.R


open class BaseActivity : AppCompatActivity() {

    protected fun replaceFragment(fragment: Fragment?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment!!)
        fragmentTransaction.commit()
    }

    protected open fun addFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(fragment.javaClass.canonicalName)
        fragmentTransaction.commit()
    }
}