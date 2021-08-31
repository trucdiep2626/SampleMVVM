package com.example.samplemvvm.model
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.caverock.androidsvg.SVGImageView
import com.example.samplemvvm.R
import com.squareup.picasso.Picasso

class CountryAdapter(var countries: MutableList<Country> , val context: Context) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    fun updateCountries(newCountries: MutableList<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

     class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

     //   var flag: SVGImageView
        var name: TextView
        var capital: TextView

        init {
          //  flag = itemView.findViewById(R.id.imgFlag)
            name = itemView.findViewById(R.id.tvCountryName)
            capital = itemView.findViewById(R.id.tvCapital)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
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




