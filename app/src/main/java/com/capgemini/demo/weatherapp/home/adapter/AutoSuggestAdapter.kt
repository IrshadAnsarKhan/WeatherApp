package com.capgemini.demo.weatherapp.home.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.capgemini.demo.weatherapp.R
import com.capgemini.demo.weatherapp.utils.NotificationHelper

class AutoSuggestAdapter(context: Context, resource: Int) : ArrayAdapter<String>(context, resource), Filterable  {

    private var mlistData: MutableList<String>? = ArrayList()

    fun setData(list: List<String>?) {
        mlistData!!.clear()
        mlistData!!.addAll(list!!)
    }

    override fun getCount(): Int {
        return if (mlistData != null && mlistData!!.size > 0) {
            mlistData!!.size
        } else 0
    }

    override fun getItem(position: Int): String? {
        return mlistData!![position]
    }

    /**
     * Used to Return the full object directly from adapter.
     *
     * @param position
     * @return
     */
    fun getObject(position: Int): String? {
        return mlistData!![position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    filterResults.values = mlistData
                    filterResults.count = mlistData!!.size
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                if (results != null && results.count > 0) {
//                    notifyDataSetChanged()
                } else if (constraint != null && constraint.length > 3) {
                    showNoDataSnackBarMessage()
//                    notifyDataSetInvalidated()
                } else {
//                    notifyDataSetInvalidated()
                }
            }
        }
    }

    private fun showNoDataSnackBarMessage() {
        try {
            val rootView =
                (context as Activity?)!!.window.decorView.findViewById<View>(R.id.content)
            NotificationHelper().setSnackBar(
                rootView,
                context!!.getString(R.string.search_no_result_found)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}