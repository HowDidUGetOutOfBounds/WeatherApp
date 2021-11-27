package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.Cities
import com.example.weatherapp.MyApplication
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSettingsMainBinding
import com.example.weatherapp.databinding.FragmentWeatherMainInfoBinding
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.MainActivityViewModelFactory


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

        setSpinnerClickListener()
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
                   Log.d("tag", "${Cities.citiesList[position]}")
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