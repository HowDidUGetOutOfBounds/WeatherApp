package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Cities
import com.example.weatherapp.MyApplication
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSettingsMainBinding
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.MainActivityViewModelFactory
import java.lang.Thread.sleep


class SettingsMainFragment : Fragment() {

    private var _binding: FragmentSettingsMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsMainBinding.inflate(inflater, container, false)

        // load existing vm
        vm = ViewModelProvider(
            requireActivity(),
            MainActivityViewModelFactory(this.activity?.application as MyApplication)
        ).get(MainActivityViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getCityList()

        setLiveDataListeners()
        setSpinnerClickListener()
    }

    private fun loadCachedSpinnerValue() {
        val cachedCityId = vm.loadCityFromCache()

        binding.chooseCitySpinner.setSelection(cachedCityId)
    }

    private fun setLiveDataListeners() {
        vm.cityListLiveData.observe(viewLifecycleOwner, { cities ->
            setCityListSpinner(cities)
            loadCachedSpinnerValue()
        })
    }

    private fun setCityListSpinner(cities: MutableList<String>) {
        binding.chooseCitySpinner.adapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, cities)
    }

    private fun setSpinnerClickListener() {
        binding.chooseCitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("tag", Cities.citiesList[position])
                    vm.saveChosenCity(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}