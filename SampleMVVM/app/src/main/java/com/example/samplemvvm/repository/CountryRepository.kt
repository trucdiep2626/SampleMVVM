package com.example.samplemvvm.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplemvvm.api.CountriesService
import com.example.samplemvvm.model.Country
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class CountryRepository: ICountryRepository {
    lateinit var service: Call<MutableList<Country>>
    var mCountries :MutableLiveData<MutableList<Country>>

    constructor( ) {
        mCountries= MutableLiveData()

    }

    override fun fetchData():MutableLiveData<MutableList<Country>>{
        service= CountriesService.create().getCountries()
        service.enqueue(object :Callback<MutableList<Country>> {
            override fun onResponse(
                call: Call<MutableList<Country>>?,
                response: Response<MutableList<Country>>?
            ) {
                if (response!!.isSuccessful && response != null) {
                    mCountries.value = response.body()
                }
            }

            override fun onFailure(call: Call<MutableList<Country>>?, t: Throwable?) {
             println("False")
            }
        })
        return mCountries
    }

}