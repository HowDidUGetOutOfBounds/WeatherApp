package com.example.weatherapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherMainInfoBinding


class WeatherMainInfoFragment : Fragment() {

    private var _binding: FragmentWeatherMainInfoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherMainInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentToolbar.inflateMenu(R.menu.main_fragment_menu)

        binding.mainFragmentToolbar.setOnMenuItemClickListener {
            when (it.itemId)
            {
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