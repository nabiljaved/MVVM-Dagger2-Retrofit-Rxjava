package com.example.countries.Model

import com.example.countries.Country
import com.example.countries.DependencyInjection.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class CountriesService
{

//    private val BASE_URL = "https://raw.githubusercontent.com/"
//    private val api :CountriesApi
//
//    init {
//        api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(CountriesApi::class.java)
//    }

    @Inject
    lateinit var  api :CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries():Single<List<Country>>{
        return api.getCountries();
    }

}