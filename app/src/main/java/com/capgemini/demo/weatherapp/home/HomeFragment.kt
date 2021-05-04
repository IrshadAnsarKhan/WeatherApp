package com.capgemini.demo.weatherapp.home

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import com.capgemini.demo.weatherapp.R
import com.capgemini.demo.weatherapp.api_service.WeatherApiRequest
import com.capgemini.demo.weatherapp.base.BaseFragment
import com.capgemini.demo.weatherapp.databinding.FragmentHomeBinding
import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.Result
import com.capgemini.demo.weatherapp.details.DetailsFragment
import com.capgemini.demo.weatherapp.home.adapter.AutoSuggestAdapter
import com.capgemini.demo.weatherapp.home.viewmodel.HomeViewModel
import com.capgemini.demo.weatherapp.home.viewmodel.HomeViewModelFactory
import com.capgemini.demo.weatherapp.retrofit.WeatherApiRetrofit
import com.capgemini.demo.weatherapp.utils.DataConverter
import com.capgemini.demo.weatherapp.utils.NotificationHelper
import com.capgemini.demo.weatherapp.view.MainActivity
import java.util.*

class HomeFragment : BaseFragment() {

    private lateinit var autoSuggestAdapter: AutoSuggestAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val TRIGGER_AUTO_COMPLETE = 100
    private val AUTO_COMPLETE_DELAY: Long = 300
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Inject dependency using dagger
        val apiRequest = WeatherApiRetrofit().getRetrofitInstance(requireContext())
            .create(WeatherApiRequest::class.java)
        val apiRepository = ApiRepository(apiRequest)
        val factory = HomeViewModelFactory(apiRepository)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchAutoCompleteView(binding.includeAutocompleteSearchLayout.autoCompleteSearch)
    }


    private fun setupSearchAutoCompleteView(autoCompleteTextView: AutoCompleteTextView) {
        autoSuggestAdapter = AutoSuggestAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1
        )
        autoCompleteTextView.dropDownWidth = resources.displayMetrics.widthPixels - 250
        autoCompleteTextView.threshold = 3
        autoCompleteTextView.setAdapter(autoSuggestAdapter)
        autoCompleteTextView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                openDetailsFragment(
                    autoSuggestAdapter.getObject(position)
                )
            }

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler?.removeMessages(TRIGGER_AUTO_COMPLETE)
                handler?.sendEmptyMessageDelayed(
                    TRIGGER_AUTO_COMPLETE,
                    AUTO_COMPLETE_DELAY
                )
            }

            override fun afterTextChanged(s: Editable) {}
        })
        handler = Handler { msg: Message ->
            if (msg.what == TRIGGER_AUTO_COMPLETE) {
                val selectedText = autoCompleteTextView.text
                if (!TextUtils.isEmpty(selectedText)) {
                    fetchSearchDataFromApi(selectedText.toString())
                }
            }
            false
        }
    }


    private fun fetchSearchDataFromApi(searchQuery: String) {
        homeViewModel.getCityMutableLiveData()
            .observe(viewLifecycleOwner) { baseApiResponseModel ->
                if (baseApiResponseModel != null && baseApiResponseModel.isSuccessful) {
                    val apiResponseData = baseApiResponseModel.apiResponseData
                    apiResponseData?.let {
                        val filteredData = DataConverter()
                            .convertToSearchModelList(it as ApiResponseModel)
                        populateSearchListData(filteredData)
                    }

                } else {
                    //TODO handle api error here
                    val errorMsgString = resources.getString(R.string.error_msg)
                    NotificationHelper().setSnackBar(binding.root, errorMsgString)
                }
            }

        homeViewModel.searchProductsResponseLiveData(searchQuery)
    }

    private fun openDetailsFragment(selectedCity: Result) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("selectedCity", selectedCity)
        detailsFragment.arguments = bundle
        (activity as MainActivity).addFragment(detailsFragment)
    }

    private fun populateSearchListData(cities: ArrayList<Result>?) {
        autoSuggestAdapter.setData(cities)
        autoSuggestAdapter.notifyDataSetChanged()
    }

}