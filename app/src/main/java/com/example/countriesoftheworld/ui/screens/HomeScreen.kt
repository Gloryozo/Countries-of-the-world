package com.example.countriesoftheworld.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesoftheworld.MainTopAppBar
import com.example.countriesoftheworld.models.Country
import com.example.countriesoftheworld.viewmodels.CountryViewModel

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController, countryViewModel: CountryViewModel = viewModel()) {
    // Add ViewModel initialization
    countryViewModel.getCountryList()

    Scaffold(
        topBar = { MainTopAppBar("Countries of the world", navController) },
    ) { innerPadding ->
        // this calls CountryList composable
        CountryList(
            modifier = Modifier.padding(innerPadding),
            country = countryViewModel.country,
            navController = navController
        )
    }
}

@Composable
fun CountryList(modifier: Modifier = Modifier, country: List<Country>,navController: NavController) {
    LazyColumn(modifier) {
        items(country) { countrylist ->
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("details/${countrylist.name.common}")  // this passes the countries name
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Flag image
                Image(
                    painter = rememberAsyncImagePainter(model = countrylist.flags.png),
                    contentDescription = "${countrylist.name.common} flag",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp, start = 16.dp)
                )
                Text(
                    text = countrylist.name.common,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
    }
}
