package com.example.samplemvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm.model.Country
import com.example.samplemvvm.repository.CountryRepository

class CountryViewModel : ViewModel() {
      private var mCountries:MutableLiveData<MutableList<Country>>
    private var countryRepository: CountryRepository

    init {
        mCountries = MutableLiveData()
        countryRepository = CountryRepository()
    }

    fun fetchData(){
        mCountries=  countryRepository.fetchData()
    }

    fun getCountries() = mCountries

}