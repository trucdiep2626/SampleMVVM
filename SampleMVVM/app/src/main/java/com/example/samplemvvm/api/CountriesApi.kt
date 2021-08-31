package com.example.samplemvvm.api

import android.media.Image
import com.example.samplemvvm.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountriesApi {

    @GET("all")
    fun getCountries(): Call<MutableList<Country>>


}