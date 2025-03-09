package com.example.countriesoftheworld.models

import androidx.compose.ui.graphics.PathSegment
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Type
import java.util.Currency

data class Country(
    val name: Name,
    val capital: List<String>? = emptyList(),
    val population: Long,
    val region: String,
    val subregion: String? = "",
    val languages: Map<String, String>? = emptyMap(),
    val flags: Flags
)

data class Name(val common: String)
data class Flags(val png: String)




const val BASE_URL = "https://restcountries.com/"

interface CountryApi{
    @GET("v3.1/all")
    suspend fun getCountry(): List<Country>


    // Get single country (using name)
    @GET("v3.1/name/{countryId}")
    suspend fun getCountryDetail( @Path("countryId") countryId: String
    ): List<Country>

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