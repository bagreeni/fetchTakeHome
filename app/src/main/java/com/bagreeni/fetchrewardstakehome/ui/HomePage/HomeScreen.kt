package com.bagreeni.fetchrewardstakehome.ui.HomePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagreeni.fetchrewardstakehome.ui.common.ListItem
import com.bagreeni.fetchrewardstakehome.ui.theme.FetchRewardsTakeHomeTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: StateFlow<HomeUiState>
) {
    val state by uiState.collectAsState()
    Column(modifier = modifier) {
        when (val current = state) {
            is HomeUiState.Error -> {
                HomeErrorScreen()
            }

            is HomeUiState.Loading -> {
                HomeLoadingScreen()
            }

            is HomeUiState.Content -> {
                HomeContentScreen(current.list)
            }
        }
    }
}

@Composable
private fun HomeContentScreen(data: List<ItemData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.surface
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(items = data) { currentItem ->
                ListItem(
                    name = currentItem.name,
                    listId = currentItem.listId?.toString()
                )
            }
        }
    }
}


@Composable
private fun HomeErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primary
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error fetching data",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun HomeLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Loading",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeErrorScreenPreview() {
    FetchRewardsTakeHomeTheme {
        HomeErrorScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeLoadingScreenPreview() {
    FetchRewardsTakeHomeTheme {
        HomeLoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentScreenPreview() {
    val data: List<ItemData> = listOf(ItemData("Item 3",1), ItemData("Item 12",1))
    HomeContentScreen(data)
}