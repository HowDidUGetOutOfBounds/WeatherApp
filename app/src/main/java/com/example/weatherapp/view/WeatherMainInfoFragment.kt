package com.example.weatherapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp.MyApplication
import com.example.weatherapp.R
import com.example.weatherapp.data_class.WeatherData
import com.example.weatherapp.databinding.FragmentWeatherMainInfoBinding
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.MainActivityViewModelFactory
import javax.inject.Inject


class WeatherMainInfoFragment : Fragment() {

    private var _binding: FragmentWeatherMainInfoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vm: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherMainInfoBinding.inflate(inflater, container, false)


        (requireActivity().application as MyApplication).appComponent.inject(this)
        //initialize viewModel(or get existing)
        //vm = ViewModelProvider(requireActivity(), MainActivityViewModelFactory(this.activity?.application as MyApplication)).get(MainActivityViewModel::class.java)


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

        vm.getCityList()

        binding.buttonLoadWeather.setOnClickListener {
            vm.getWeatherInfo()
        }

        binding.buttonTest.setOnClickListener {
            findNavController().navigate(WeatherMainInfoFragmentDirections.actionWeatherMainInfoFragmentToSettingsMainFragment())
        }



        vm.weatherInfoLiveData.observe(viewLifecycleOwner, Observer {
            setWeatherInfo(it)
        })

        vm.weatherInfoFailureLiveData.observe(viewLifecycleOwner, Observer {
            binding.outputGroup.visibility = View.GONE
        })

        vm.progressBarLiveData.observe(viewLifecycleOwner, Observer {
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        })

    }

    private fun setWeatherInfo(info: WeatherData) {
        with(binding) {
            outputGroup.visibility = View.VISIBLE

            layoutWeatherBasic.tvDateTime.visibility = View.GONE

            layoutWeatherBasic.tvDateTime.text = info.dateTime
            layoutWeatherBasic.tvTemperature.text = info.temperature
            layoutWeatherBasic.tvCityCountry.text = info.cityAndCountry
            Glide.with(requireContext()).load(info.weatherConditionIconUrl.replace("http","https")).into(layoutWeatherBasic.ivWeatherCondition)
            layoutWeatherBasic.tvWeatherCondition.text = info.weatherConditionIconDescription

            layoutWeatherAdditional.tvHumidityValue.text = info.humidity
            layoutWeatherAdditional.tvPressureValue.text = info.pressure
            layoutWeatherAdditional.tvVisibilityValue.text = info.visibility

            layoutSunsetSunrise.tvSunriseTime.text = info.sunrise
            layoutSunsetSunrise.tvSunsetTime.text = info.sunset
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}