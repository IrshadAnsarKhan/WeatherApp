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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capgemini.demo.weatherapp.R
import com.capgemini.demo.weatherapp.api_service.WeatherApiRequest
import com.capgemini.demo.weatherapp.base.BaseFragment
import com.capgemini.demo.weatherapp.databinding.FragmentHomeBinding
import com.capgemini.demo.weatherapp.datamodel.search.Result
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel
import com.capgemini.demo.weatherapp.db.room.DatabaseBuilder
import com.capgemini.demo.weatherapp.db.room.DatabaseHelperImpl
import com.capgemini.demo.weatherapp.details.DetailsFragment
import com.capgemini.demo.weatherapp.home.adapter.AutoSuggestAdapter
import com.capgemini.demo.weatherapp.home.adapter.RecentSearchRecyclerViewAdapter
import com.capgemini.demo.weatherapp.home.viewmodel.HomeViewModel
import com.capgemini.demo.weatherapp.home.viewmodel.HomeViewModelFactory
import com.capgemini.demo.weatherapp.retrofit.WeatherApiRetrofit
import com.capgemini.demo.weatherapp.utils.DataConverter
import com.capgemini.demo.weatherapp.utils.NotificationHelper
import com.capgemini.demo.weatherapp.view.MainActivity
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var weatherApiRetrofit: WeatherApiRetrofit

    @Inject
    lateinit var dbHelper: DatabaseHelperImpl

    @Inject
    lateinit var dataConverter: DataConverter

    @Inject
    lateinit var notificationHelper: NotificationHelper

    private lateinit var autoSuggestAdapter: AutoSuggestAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val TRIGGER_AUTO_COMPLETE = 100
    private val AUTO_COMPLETE_DELAY: Long = 300
    private var handler: Handler? = null
    private lateinit var adapter: RecentSearchRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiRequest = weatherApiRetrofit.getRetrofitInstance()
            .create(WeatherApiRequest::class.java)
        val apiRepository = ApiRepository(apiRequest)
        val factory = HomeViewModelFactory(apiRepository, dbHelper)
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
        initObservers()
        setupSearchAutoCompleteView(binding.includeAutocompleteSearchLayout.autoCompleteSearch)
    }

    override fun onResume() {
        super.onResume()
        binding.includeAutocompleteSearchLayout.autoCompleteSearch.text.clear()
        homeViewModel.fetchSearchedData()
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
            OnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
                onAutoCompleteRowClick(autoSuggestAdapter.getObject(position))
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


    private fun onAutoCompleteRowClick(result: Result) {
        binding.includeAutocompleteSearchLayout.autoCompleteSearch.text.clear()
        (activity as MainActivity).hideKeyboard()
        val weatherRoomDataModel = homeViewModel.convertToDbModel(result, dataConverter)
        homeViewModel.insertData(weatherRoomDataModel)
        homeViewModel.fetchSearchedData()
        openDetailsFragment(weatherRoomDataModel)
    }

    private fun initObservers() {

        homeViewModel.getDbDataMutableLiveData().observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                showRecentSearchList(it)
            } else {
                showNoRecentSearchDataUI()
            }

        }
        homeViewModel.getCityMutableLiveData()
            .observe(viewLifecycleOwner) { baseApiResponseModel ->
                if (baseApiResponseModel != null && baseApiResponseModel.isSuccessful) {
                    val apiResponseData = baseApiResponseModel.apiResponseData
                    apiResponseData?.let {
                        val filteredData = homeViewModel.filterData(it, dataConverter)
                        populateSearchListData(filteredData)
                    }

                } else {
                    //TODO handle api error here
                    val errorMsgString = resources.getString(R.string.error_msg)
                    notificationHelper.setSnackBar(binding.root, errorMsgString)
                }
            }
    }

    private fun showNoRecentSearchDataUI() {
        binding.recyclerView.visibility = View.GONE
        binding.tvNoData.visibility = View.VISIBLE
    }

    private fun showRecentSearchList(weatherData: List<WeatherRoomDataModel>) {
        adapter = RecentSearchRecyclerViewAdapter(this, weatherData)
        binding.recyclerView.visibility = View.VISIBLE
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val itemDecoration =
            DividerItemDecoration(requireContext(), linearLayoutManager.orientation)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.tvNoData.visibility = View.GONE
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun fetchSearchDataFromApi(searchQuery: String) {
        homeViewModel.searchProductsResponseLiveData(searchQuery)
    }

    private fun openDetailsFragment(selectedCity: WeatherRoomDataModel) {
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

    fun onRowItemClicked(weatherRoomDataModel: WeatherRoomDataModel) {
        openDetailsFragment(weatherRoomDataModel)
    }

}