package com.bagreeni.fetchrewardstakehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

            LaunchedEffect("fetchingInitialData") {
                homeScreenViewModel.getData()
            }

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchRewardsTakeHomeTheme {
        Greeting("Android")
    }
}