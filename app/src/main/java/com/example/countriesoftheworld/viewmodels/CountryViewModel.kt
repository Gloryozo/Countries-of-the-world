package com.example.countriesoftheworld.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesoftheworld.models.Country
import com.example.countriesoftheworld.models.CountryApi
import kotlinx.coroutines.launch

class CountryViewModel: ViewModel() {
    var country by mutableStateOf<Country?>(null)

    fun getCountry(){
        viewModelScope.launch{
            var countryApi: CountryApi? = null
            try{
                countryApi = CountryApi.getInstance()
                country = countryApi.getCountry()
                Log.d("COUNTRY", country.toString())
            }catch(e: Exception){
                Log.d("ERROR", e.message.toString())
            }
        }
    }
}