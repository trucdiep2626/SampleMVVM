package com.example.samplemvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplemvvm.R
import com.example.samplemvvm.viewModel.LogInViewModel
import com.example.samplemvvm.databinding.ActivityHomeBinding
import com.example.samplemvvm.model.Country
import com.example.samplemvvm.viewModel.CountryViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var logInViewModel: LogInViewModel
    lateinit var countryViewModel: CountryViewModel
    lateinit var adapter: CountryAdapter
    lateinit var countries: MutableList<Country>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_home
        )
        logInViewModel = ViewModelProvider(this)[LogInViewModel::class.java]
        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        authentication()
        setListener()

        val linearLayoutManager =
            LinearLayoutManager(this)
        binding.rvCountriesList.layoutManager = linearLayoutManager
        countries = mutableListOf()
        adapter = CountryAdapter(countries, this)
        binding.rvCountriesList.adapter = adapter
        countryViewModel.fetchData()
        countryViewModel.getCountries().observe(this, Observer<MutableList<Country>> {
            if (it != null) {
                onSuccessful(it)
                println("get data successful")
            } else {
                onError()
            }
        })

    }


    private fun authentication() {

        logInViewModel.mCurrentUser.observe(this, Observer {
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

        binding.edtSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    val filterCountries = countries?.filter { country ->
                        country.name.contains(s.toString(), true)
                    }
                    filterCountries?.let { adapter.updateCountries(it as MutableList<Country>) }
                } else {
                    countries?.let { adapter.updateCountries(it) }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })

    }

    fun onSuccessful(result: MutableList<Country>) {
        binding.rvCountriesList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.edtSearch.isEnabled = true

        countries = result
//        countries.forEach {
//            println(it.name)
//        }
        //  adapter= CountryAdapter(countries,this)
        adapter.updateCountries(countries)
        binding.rvCountriesList.adapter = adapter
        println(adapter.itemCount)
    }

    fun onError() {
        binding.rvCountriesList.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.edtSearch.isEnabled = false
        binding.tvNoData.visibility = View.VISIBLE

        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

}