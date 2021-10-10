package com.example.countries.DependencyInjection

import com.example.countries.Model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent
{
    fun inject(service:CountriesService)
}