package com.example.samplemvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.CountryApplication
import com.example.samplemvvm.R
import com.example.samplemvvm.databinding.ActivityHomeBinding
import com.example.samplemvvm.model.Country
import com.example.samplemvvm.viewModel.CountryViewModel
import com.example.samplemvvm.viewModel.LogInViewModel
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var logInViewModel: LogInViewModel

    @Inject
    lateinit var countryViewModel: CountryViewModel
    lateinit var recycleView: RecyclerView
    private lateinit var binding: ActivityHomeBinding
    lateinit var adapter: CountryAdapter
    lateinit var countries: MutableList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_home
        )

        val appComponent = (application as CountryApplication).appComponent
        appComponent.inject(this)

        binding.lifecycleOwner = this
        binding.logInViewModel = logInViewModel
        binding.countryViewModel = countryViewModel
        recycleView = binding.rvCountriesList


        countries = mutableListOf()
        adapter = CountryAdapter(countries)
        recycleView.adapter = adapter
        authentication()


        countryViewModel.fetchData()
        countryViewModel.getCountries().observe(this, {
            if (it != null) {
                println("get data")
                onSuccessful(it as MutableList<Country>)
            } else {
                onError()
            }

        })

    }


    private fun authentication() {

        logInViewModel.mCurrentUser.observe(this, {
            if (it == null) {
                startActivity(Intent(this, LogInActivity::class.java))
                finish()
            }
        })

    }


    private fun onSuccessful(result: MutableList<Country>) {
        countries = result
        adapter.updateCountries(countries)
        recycleView.adapter = adapter
    }

    private fun onError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

}