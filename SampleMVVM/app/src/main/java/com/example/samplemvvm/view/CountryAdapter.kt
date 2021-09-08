package com.example.samplemvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.R
import com.example.samplemvvm.databinding.CountryItemBinding
import com.example.samplemvvm.model.Country


class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    var countries: MutableList<Country>


    constructor(countries: MutableList<Country>) : super() {
        this.countries = countries
    }

    fun updateCountries(newCountries: MutableList<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    class CountryViewHolder  : RecyclerView.ViewHolder  {
        var  countryItemBinding: CountryItemBinding
     constructor(countryItemBinding: CountryItemBinding): super(countryItemBinding.root){

         this.countryItemBinding=countryItemBinding
     }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        return CountryViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
//        )
        val countryItemBinding: CountryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.country_item, parent, false
        )
        return CountryViewHolder(countryItemBinding)
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        println(countries.size)
        val country = countries[position]
        holder.countryItemBinding.country=country
//        holder.name.text = country.name
//        holder.capital.text = country.capital

    }

    override fun getItemCount(): Int {
        return countries.size
    }
}




