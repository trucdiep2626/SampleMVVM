package com.example.samplemvvm.di

import android.app.Application
import com.example.samplemvvm.view.HomeActivity
import com.example.samplemvvm.view.LogInActivity
import com.example.samplemvvm.viewModel.CountryViewModel
import com.example.samplemvvm.viewModel.LogInViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun getCountryViewModel(): CountryViewModel

    fun inject(activity: HomeActivity)

    fun getLogInViewModel(): LogInViewModel

    fun inject(activity: LogInActivity)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application):Builder

        fun build():AppComponent
    }
}