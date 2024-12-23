package com.bagreeni.fetchrewardstakehome.ui.HomePage

import androidx.lifecycle.ViewModel
import com.bagreeni.fetchrewardstakehome.backend.FetchApiService
import com.bagreeni.fetchrewardstakehome.backend.FetchItem
import com.bagreeni.fetchrewardstakehome.backend.HiringResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val fetchApi: FetchApiService) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    suspend fun getData() {
        _homeUiState.value = HomeUiState.Loading
        when (val response = fetchApi.getInfo()) {
            is HiringResponse.Success -> {
                _homeUiState.value = HomeUiState.Content(
                    list = hiringListToSortedUiDataNumerical(response.hiringList)
                )
            }

            else -> {
                _homeUiState.value = HomeUiState.Error
            }
        }
    }

    /**
     * converts to UI objects, filters out null names, and sorts list based on listId and then name
     * assuming the name is by alphabetical and not numerical.
     * eg. item 234 comes be for item 25 in alphabetical.
     */
    internal fun hiringListToSortedUiDataAlphabetical(hiringList: List<FetchItem>): List<ItemData> {
        val sortedList = hiringList.map { item ->
            ItemData(
                name = item.name ?: "",
                listId = item.listId
            )
        }.filter {
            it.name.isNotBlank()
        }.sortedWith(compareBy({ it.listId }, { it.name }))

        return sortedList
    }

    /**
     * converts to UI objects, filters out null names, and sorts list based on listId and then name
     * assuming the name follows the pattern of "item XXX"
     * eg. item 24 comes be for item 235 in alphabetical.
     */
    internal fun hiringListToSortedUiDataNumerical(hiringList: List<FetchItem>): List<ItemData> {
        val sortedList = hiringList.map { item ->
            ItemData(
                name = item.name ?: "",
                listId = item.listId
            )
        }.filter {
            it.name.isNotBlank()
        }.sortedWith(
            compareBy({ it.listId },
                { it.name.lowercase().substringAfter("item ").toInt() })
        )

        return sortedList
    }
}