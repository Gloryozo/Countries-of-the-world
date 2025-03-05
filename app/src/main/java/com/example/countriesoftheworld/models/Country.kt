package com.example.countriesoftheworld.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Country(
    val name: Name,
    val population: Long,
    val region: String,
    val flags: Flags
)
data class Name(val common: String)
data class Flags(val png: String)

const val BASE_URL = "https://restcountries.com/"

interface CountryApi{
    @GET("v3.1/all")
    suspend fun getCountry(): List<Country>

    companion object{
        var countryService: CountryApi? = null

         fun getInstance(): CountryApi {
             if (countryService === null) {
                 countryService = Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build().create(CountryApi::class.java)
             }
             return countryService!!

         }
   }
}