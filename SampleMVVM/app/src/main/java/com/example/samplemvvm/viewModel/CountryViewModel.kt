package com.example.samplemvvm.viewModel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm.model.Country
import com.example.samplemvvm.repository.CountryRepository
import com.example.samplemvvm.view.CountryAdapter
import javax.inject.Inject

class CountryViewModel @Inject constructor(var countryRepository: CountryRepository) : ViewModel() {
    lateinit var mCountries: MutableLiveData<MutableList<Country>>

    fun fetchData() {
        mCountries = countryRepository.fetchData()
    }

    fun getCountries() = mCountries

    fun search(adapter: CountryAdapter): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var filterCountries: MutableList<Country> = mutableListOf()
                if (s.isNotEmpty()) {
                    filterCountries = mCountries.value!!
                    filterCountries = filterCountries.filter { country ->
                        country.name.contains(s.toString(), true)
                    } as MutableList<Country>
                    filterCountries.let { adapter.updateCountries(it as MutableList<Country>) }
                } else {
                    filterCountries.let {
                        mCountries.value?.let { it1 ->
                            adapter.updateCountries(
                                it1
                            )
                        }
                    }
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
        }
    }

}

