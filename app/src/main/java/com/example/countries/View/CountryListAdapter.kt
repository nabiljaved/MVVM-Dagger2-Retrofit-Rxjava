package com.example.countries.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.Country
import com.example.countries.R
import com.example.countries.util.getProgressDrawable
import com.example.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*


class CountryListAdapter(var countries:ArrayList<Country>):RecyclerView.Adapter<CountryListAdapter.MyViewHolder>()
{

    fun updateCountries(newCountries:List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  MyViewHolder (
                LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(countries[position])

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val countryName = itemView.name;
        private val imageView = itemView.imageView
        private val countryCapital = itemView.capital
        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            countryCapital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)
         }

    }

}