package com.example.samplemvvm

import android.app.Application
import com.example.samplemvvm.di.AppComponent
import com.example.samplemvvm.di.DaggerAppComponent

class CountryApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
     appComponent = DaggerAppComponent.builder().application(this).build()
    }
}