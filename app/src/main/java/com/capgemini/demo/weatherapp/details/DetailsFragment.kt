package com.capgemini.demo.weatherapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.capgemini.demo.weatherapp.base.BaseFragment
import com.capgemini.demo.weatherapp.databinding.FragmentDetailsBinding
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel
import com.capgemini.demo.weatherapp.details.viewmodel.DetailsViewModel
import com.capgemini.demo.weatherapp.home.HomeFragment

class DetailsFragment : BaseFragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private var roomDataModel: WeatherRoomDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomDataModel = arguments?.let { detailsViewModel.getData(it) }
        roomDataModel?.let { populateResult(it) }
    }

    private fun populateResult(roomDataModel: WeatherRoomDataModel) {
        binding.tvAreaName.text = roomDataModel.areaName
        binding.tvCountry.text = roomDataModel.country
        binding.tvRegion.text = roomDataModel.region
        binding.tvWeatherUrl.text = roomDataModel.weatherUrl
        binding.tvGeoLocation.text = detailsViewModel.getGeoLocationText(roomDataModel)
        binding.tvPopulation.text = roomDataModel.population
    }

}