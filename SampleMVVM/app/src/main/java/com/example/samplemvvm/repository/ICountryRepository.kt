package com.example.samplemvvm.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplemvvm.model.Country

interface ICountryRepository {
    fun fetchData(): MutableLiveData<MutableList<Country>>
}