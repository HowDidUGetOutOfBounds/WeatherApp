package com.example.weatherapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.MyApplication
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherMainInfoBinding
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.MainActivityViewModelFactory


class WeatherMainInfoFragment : Fragment() {

    private var _binding: FragmentWeatherMainInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherMainInfoBinding.inflate(inflater, container, false)

        //initialize viewModel(or get existing)
        vm = ViewModelProvider(requireActivity(), MainActivityViewModelFactory(this.activity?.application as MyApplication)).get(MainActivityViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentToolbar.inflateMenu(R.menu.main_fragment_menu)

        binding.mainFragmentToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    findNavController().navigate(WeatherMainInfoFragmentDirections.actionWeatherMainInfoFragmentToSettingsMainFragment())
                    true
                }
                else -> false
            }
        }

        //TODO remove
        binding.buttonTest.setOnClickListener {
            findNavController().navigate(WeatherMainInfoFragmentDirections.actionWeatherMainInfoFragmentToSettingsMainFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}