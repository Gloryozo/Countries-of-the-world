package com.example.countriesoftheworld.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesoftheworld.models.Country
import com.example.countriesoftheworld.models.CountryApi
import kotlinx.coroutines.launch

class CountryViewModel: ViewModel() {
    var country = mutableStateListOf<Country>()
    var countryDetail: Country? by mutableStateOf(null)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    init {
        getCountryList()
    }
    // Make public and call from HomeScreen
    fun getCountryList(){
        viewModelScope.launch{
            try{
                val countryApi = CountryApi.getInstance()
                val result = countryApi.getCountry()
                country.clear()
                country.addAll(result)
            }catch(e: Exception){
                Log.e("VIEWMODEL", "Error: ${e.message}")
            }
        }
    }
    // New function to get country details
    fun getCountryDetail(countryId: String) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                val countryApi = CountryApi.getInstance()
                val response = countryApi.getCountryDetail(countryId)
                countryDetail = response.firstOrNull()
                if (countryDetail == null) error = "Country not found"
            } catch (e: Exception) {
                error = e.message ?: "Unknown error"
                Log.e("DETAIL", "Error fetching country details: ${e.message}")
                countryDetail = null
            } finally {
                isLoading = false
            }
        }
            }

    }






