package com.example.samplemvvm.di

import com.example.samplemvvm.api.CountriesApi
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
  class AppModule {

    @Provides
      fun provideRetrofit(): CountriesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CountriesApi::class.java)
    }

    @Provides
    fun getFireBaseAuth()= FirebaseAuth.getInstance()
}