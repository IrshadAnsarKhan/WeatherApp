package com.capgemini.demo.weatherapp.details

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.capgemini.demo.weatherapp.R
import com.capgemini.demo.weatherapp.api_service.WeatherApiRequest
import com.capgemini.demo.weatherapp.base.BaseFragment
import com.capgemini.demo.weatherapp.databinding.FragmentDetailsBinding
import com.capgemini.demo.weatherapp.datamodel.city_wheather.WeatherApiResponseModel
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel
import com.capgemini.demo.weatherapp.details.viewmodel.DetailViewModelFactory
import com.capgemini.demo.weatherapp.details.viewmodel.DetailsViewModel
import com.capgemini.demo.weatherapp.home.ApiRepository
import com.capgemini.demo.weatherapp.retrofit.WeatherApiRetrofit
import com.capgemini.demo.weatherapp.utils.ImageUtils
import com.capgemini.demo.weatherapp.utils.NotificationHelper


class DetailsFragment : BaseFragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private var roomDataModel: WeatherRoomDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiRequest = WeatherApiRetrofit().getRetrofitInstance(requireContext())
            .create(WeatherApiRequest::class.java)
        val apiRepository = ApiRepository(apiRequest)
        val factory = DetailViewModelFactory(apiRepository)
        detailsViewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
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
        initObserver()
        roomDataModel?.let { fetchWeatherDetailsFromApi(it) }
    }

    private fun initObserver() {
        detailsViewModel.getWeatherMutableLiveData()
            .observe(viewLifecycleOwner) { baseApiResponseModel ->
                hideProgressDialog()
                if (baseApiResponseModel != null && baseApiResponseModel.isSuccessful) {
                    val apiResponseData = baseApiResponseModel.apiResponseData
                    apiResponseData?.let {
                        populateResult(it as WeatherApiResponseModel)
                    }

                } else {
                    //TODO handle api error here
                    val errorMsgString = resources.getString(R.string.error_msg)
                    NotificationHelper().setSnackBar(binding.root, errorMsgString)
                }
            }
    }

    private fun fetchWeatherDetailsFromApi(roomDataModel: WeatherRoomDataModel) {
        showProgressDialog()
        detailsViewModel.fetchWeatherResponseLiveData(roomDataModel.areaName.toString())
    }

    private fun populateResult(weatherApiResponseModel: WeatherApiResponseModel) {
        val currentCondition = weatherApiResponseModel.data.current_condition[0]

        ImageUtils(requireContext()).loadImagesToView(
            binding.imgViewWeatherImage,
            currentCondition.weatherIconUrl[0].value
        )
        binding.tvAreaName.text = weatherApiResponseModel.data.request[0].query.toString()
        binding.tvHumidity.text = currentCondition.humidity.toString()
        binding.tvWeatherDesc.text = currentCondition.weatherDesc[0].value
        binding.tvTemperature.text = detailsViewModel.getTemperatureText(currentCondition)
    }

    private fun showProgressDialog() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun hideProgressDialog() {
        binding.progressBar.visibility = View.GONE
    }
}