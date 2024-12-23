package com.bagreeni.fetchrewardstakehome.ui.HomePage

sealed class HomeUiState(){
    data class Content(
        val list: List<ItemData>
    ) : HomeUiState()
    data object Loading : HomeUiState()
    data object Error : HomeUiState()
}
