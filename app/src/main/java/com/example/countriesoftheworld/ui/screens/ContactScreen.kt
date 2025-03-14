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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countriesoftheworld.R
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
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.for_more_information_contact_us),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.email_support_countryoftheworldapp_com),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium

        )
        Text(
            text = stringResource(R.string.phone_358_123_456_7890),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.address),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}