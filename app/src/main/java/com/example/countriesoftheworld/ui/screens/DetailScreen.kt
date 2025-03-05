package com.example.countriesoftheworld.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countriesoftheworld.ScreenTopBar



@Composable
fun DetailScreen(countryName: String?,navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Scaffold(
            topBar = { ScreenTopBar("Country: $countryName", navController) },
        ) { innerPadding ->
            Text(text = "Country details Screen", modifier = Modifier.padding(innerPadding))
        }
    }
}