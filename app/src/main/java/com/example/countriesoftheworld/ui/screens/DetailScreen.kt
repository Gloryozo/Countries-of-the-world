package com.example.countriesoftheworld.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesoftheworld.ScreenTopBar
import com.example.countriesoftheworld.models.Country
import com.example.countriesoftheworld.ui.components.ShowError
import com.example.countriesoftheworld.ui.components.ShowLoader
import com.example.countriesoftheworld.viewmodels.CountryViewModel

@Composable
fun DetailScreen(countryId: String?, navController: NavController, countryViewModel: CountryViewModel = viewModel()) {
    // Fetch the country details when the screen is launched
    LaunchedEffect(countryId) {
        if (countryId != null) {
            countryViewModel.getCountryDetail(countryId)
        }
    }

    Scaffold(
        topBar = { ScreenTopBar("Country: ${countryId ?: "Unknown"}", navController) },
    ) { innerPadding ->
        CountryDetails(
            modifier = Modifier.padding(innerPadding),
            country = countryViewModel.countryDetail)
        when {
            countryViewModel.isLoading -> ShowLoader()
            countryViewModel.error != null -> ShowError(countryViewModel.error)
            else -> CountryDetails( 
                modifier = Modifier, 
                country = countryViewModel.countryDetail)
        }
    }
}



@Composable
fun CountryDetails(modifier: Modifier = Modifier, country: Country?) {
    Column(modifier = modifier.padding(all = 24.dp)) {

        Text(text = "Name: ${country?.name?.common ?: "Unknown"}")
        Image(
            painter = rememberAsyncImagePainter(country?.flags?.png),
            contentDescription = "Flag of ${country?.name?.common}",
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(text = "Population: ${country?.population?.toString() ?: "Not available"}")
        Text(text = "Region: ${country?.region ?: "Not available"}")
    }
}