package com.example.samplemvvm.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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


        binding.rvCountriesList.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvCountriesList.layoutManager = linearLayoutManager

        countries = mutableListOf()
        adapter = CountryAdapter(countries)
        binding.rvCountriesList.adapter = adapter
        authentication()
        setListener()

        countryViewModel.fetchData()
        countryViewModel.getCountries().observe(this, {
            if (it != null) {
                println("get data")
                onSuccessful(it)
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

    private fun setListener() {
        binding.btnLogout.setOnClickListener {
            logInViewModel.logOut()
        }

        binding.edtSearch.addTextChangedListener(countryViewModel.search(adapter))

    }

    private fun onSuccessful(result: MutableList<Country>) {
        binding.rvCountriesList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.edtSearch.isEnabled = true

        countries = result
        adapter.updateCountries(countries)
        binding.rvCountriesList.adapter = adapter
    }

    private fun onError() {
        binding.rvCountriesList.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.edtSearch.isEnabled = false
        binding.tvNoData.visibility = View.VISIBLE

        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

}