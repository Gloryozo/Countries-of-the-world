package com.example.countriesoftheworld.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.countriesoftheworld.MainTopAppBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopAppBar("Countries of the world",navController) },
    ) { innerPadding ->
        Text(text="Home Screen",modifier = Modifier.padding(innerPadding))
    }
}