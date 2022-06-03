package com.example.countriesapp

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountriesApi {
    @GET("name/{name}")
    fun getCountryByName(@Path("name") country: String):
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v3.1/")
    .build()

var restCountriesApi = retrofit.create(RestCountriesApi::class.java)