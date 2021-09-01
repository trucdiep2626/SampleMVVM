package com.example.samplemvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm.model.Country
import com.example.samplemvvm.repository.CountryRepository
import javax.inject.Inject

class CountryViewModel @Inject  constructor(var countryRepository: CountryRepository) : ViewModel (){
    lateinit var mCountries: MutableLiveData<MutableList<Country>>

    fun fetchData() {
        mCountries = countryRepository.fetchData()
    }

    fun getCountries() = mCountries

}