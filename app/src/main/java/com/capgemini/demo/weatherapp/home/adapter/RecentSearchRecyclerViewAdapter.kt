package com.capgemini.demo.weatherapp.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.demo.weatherapp.R
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel
import com.capgemini.demo.weatherapp.home.HomeFragment

class RecentSearchRecyclerViewAdapter(
    private val fragment: Fragment,
    private val weatherRoomDataModels: List<WeatherRoomDataModel>
) :
    RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchRecyclerViewAdapter.WeatherViewHolder {
        val view: View =
            LayoutInflater.from(fragment.context)
                .inflate(R.layout.row_recent_search, parent, false)
        return WeatherViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: RecentSearchRecyclerViewAdapter.WeatherViewHolder,
        position: Int
    ) {
        val curr = weatherRoomDataModels[position]
        holder.status.text = """${curr.areaName}, ${curr.country}"""
        holder.status.setOnClickListener(View.OnClickListener { (fragment as HomeFragment).onRowItemClicked(curr) })
    }

    override fun getItemCount(): Int {
        return if (weatherRoomDataModels != null && !weatherRoomDataModels.isNullOrEmpty())
            weatherRoomDataModels.size
        else
            0;
    }


    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var status: TextView = itemView.findViewById(R.id.tvCityName)


    }
}