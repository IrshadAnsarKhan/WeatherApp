package com.capgemini.demo.weatherapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.capgemini.demo.weatherapp.base.BaseFragment
import com.capgemini.demo.weatherapp.databinding.FragmentDetailsBinding
import com.capgemini.demo.weatherapp.datamodel.Result
import com.capgemini.demo.weatherapp.details.viewmodel.DetailsViewModel

class DetailsFragment : BaseFragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = arguments?.let { detailsViewModel.getData(it) }
        populateResult(result)
    }

    private fun populateResult(result: Result?) {
        binding.tvAreaName.text = result?.areaName?.get(0)?.value
        binding.tvCountry.text = result?.country?.get(0)?.value
        binding.tvRegion.text = result?.region?.get(0)?.value
        binding.tvWeatherUrl.text = result?.weatherUrl?.get(0)?.value
        binding.tvGeoLocation.text = detailsViewModel.getGeoLocationText(result)
        binding.tvPopulation.text = result?.population?.toString()
    }
}