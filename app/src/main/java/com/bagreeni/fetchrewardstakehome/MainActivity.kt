package com.bagreeni.fetchrewardstakehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.bagreeni.fetchrewardstakehome.ui.HomePage.HomeScreen
import com.bagreeni.fetchrewardstakehome.ui.HomePage.HomeScreenViewModel
import com.bagreeni.fetchrewardstakehome.ui.theme.FetchRewardsTakeHomeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val homeScreenViewModel: HomeScreenViewModel by viewModels()

        setContent {
            FetchRewardsTakeHomeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Top bar spacer
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = homeScreenViewModel.homeUiState
                    )
                }
            }
        }
    }
}