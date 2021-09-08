package com.example.samplemvvm.viewModel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.model.Country
import com.example.samplemvvm.repository.CountryRepository
import com.example.samplemvvm.view.CountryAdapter
import javax.inject.Inject

class CountryViewModel @Inject constructor(var countryRepository: CountryRepository) : ViewModel() {
    lateinit var mCountries: MutableLiveData<List<Country>>
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    var isFailed: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchData() {
        isLoading.postValue(true)
        isSuccess.postValue(false)
        isFailed.postValue(false)
        mCountries = countryRepository.fetchData() as MutableLiveData<List<Country>>
        isLoading.postValue(false)
        if (mCountries.value?.isEmpty() == true) {
            isSuccess.postValue(false)
            isFailed.postValue(true)
        } else {
            isSuccess.postValue(true)
            isFailed.postValue(false)
        }

    }

    fun getCountries() = mCountries

    fun search(rvCountries: RecyclerView): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var filterCountries: MutableList<Country> = mutableListOf()
                var adapter: CountryAdapter = CountryAdapter(filterCountries)

                if (s.isNotEmpty()) {
                    filterCountries = mCountries.value!! as MutableList<Country>
                    filterCountries = filterCountries.filter { country ->
                        country.name.contains(s.toString(), true)
                    } as MutableList<Country>
                    filterCountries.let { adapter.updateCountries(it) }
                } else {
                    filterCountries.let {
                        mCountries.value?.let { it1 ->
                            adapter.updateCountries(
                                it1 as MutableList<Country>
                            )
                        }
                    }
                }
                rvCountries.adapter = adapter
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

