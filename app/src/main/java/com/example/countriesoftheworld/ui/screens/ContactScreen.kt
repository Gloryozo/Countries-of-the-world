package com.example.countriesoftheworld.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countriesoftheworld.ScreenTopBar

@Composable
fun ContactScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Contact",navController) },
    ) { innerPadding ->
        ContactDetail(Modifier.padding(innerPadding))
    }
}

@Composable
fun ContactDetail(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contact Us",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Email: support@countryapp.com",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Phone: +1 123 456 7890",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Address: 123 Main St, Anytown, USA",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}