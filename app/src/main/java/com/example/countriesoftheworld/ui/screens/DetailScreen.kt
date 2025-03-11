package com.example.countriesoftheworld.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesoftheworld.ScreenTopBar
import com.example.countriesoftheworld.models.Country
import com.example.countriesoftheworld.ui.components.ShowError
import com.example.countriesoftheworld.ui.components.ShowLoader
import com.example.countriesoftheworld.viewmodels.CountryViewModel
import java.text.DecimalFormat

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
        when {
            countryViewModel.isLoading -> ShowLoader()
            countryViewModel.error != null -> ShowError(countryViewModel.error)
            else -> CountryDetails(
                modifier = Modifier.padding(innerPadding),
                country = countryViewModel.countryDetail
            )
        }
    }
}


@Composable
fun CountryDetails(modifier: Modifier = Modifier, country: Country?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Flag Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(country?.flags?.png),
                    contentDescription = "Flag of ${country?.name?.common}",
                    modifier = Modifier
                            .fillMaxWidth(0.8f)
                        .aspectRatio(3f/2f)
                        .size(400.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = country?.name?.common ?: "Unknown Country",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Info Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                country?.let {
                    DetailRow("Capital: ", it.capital?.firstOrNull())
                    DetailRow("Population: ", it.population?.formatWithCommas())
                    DetailRow("Region: ", it.region)
                    DetailRow("Languages: ", it.languages?.values?.joinToString())
                   } ?: Text("No country data available", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}



@Composable
private fun DetailRow(label: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// Keep this extension for number formatting
fun Number.formatWithCommas() = DecimalFormat("#,###").format(this)
