package com.example.countries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.Country
import com.example.countries.Model.CountriesService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.single.SingleDoOnDispose
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryLoaderError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

//    private val countriesService = CountriesService()
//    private val disposible = CompositeDisposable()

    @Inject
    lateinit var countriesService;


    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){

        loading.value = true
        disposible.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryLoaderError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        countryLoaderError.value = true
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }


//    private fun fetchCountries2(){
//        val mockData:List<Country> = listOf(
//            Country("Pakistan"),
//            Country("India"),
//            Country("Japan"),
//            Country("Ausralia"),
//            Country("Taiwan"),
//            Country("Indonesia"),
//            Country("Singapore"),
//            Country("Phillipines"),
//            Country("Afghanistan"),
//            Country("Iran"),
//            Country("Balouchistan"),
//            Country("UAE"),
//            Country("Saudiarabia"),
//            Country("Belarus"),
//            Country("Russia"),
//            Country("Finland"),
//            Country("Norway"),
//            Country("Scandanevia"),
//            )
//
//        loading.value=true
//        countryLoaderError.value = false
//        loading.value = false
//        countries.value = mockData
//    }



}