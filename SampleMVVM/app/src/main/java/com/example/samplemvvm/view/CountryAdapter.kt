package com.example.samplemvvm.view
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.R
import com.example.samplemvvm.model.Country

class CountryAdapter(var countries: MutableList<Country>, val context: Context) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    fun updateCountries(newCountries: MutableList<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

     class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView
        var capital: TextView

        init {
            name = itemView.findViewById(R.id.tvCountryName)
            capital = itemView.findViewById(R.id.tvCapital)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return  CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
      println(countries.size)
        val country= countries.get(position)
        holder.name.setText(country.name)
        holder.capital.setText( country.capital)

    }

    override fun getItemCount(): Int{
        return countries.size
    }
}




