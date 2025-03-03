package com.bagreeni.fetchrewardstakehome.ui.HomePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagreeni.fetchrewardstakehome.backend.FetchApiService
import com.bagreeni.fetchrewardstakehome.backend.FetchItem
import com.bagreeni.fetchrewardstakehome.backend.HiringResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val fetchApi: FetchApiService) : ViewModel() {
    private val triggerFlow: MutableSharedFlow<Unit> = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST).apply {
        tryEmit(Unit)
    }
    val homeUiState: StateFlow<HomeUiState> = triggerFlow.map{ getData() }.stateIn(viewModelScope, SharingStarted.Lazily, HomeUiState.Loading)

    suspend fun getData() : HomeUiState {
        return when (val response = fetchApi.getInfo()) {
            is HiringResponse.Success -> {
                  HomeUiState.Content(
                    list = hiringListToSortedUiDataNumerical(response.hiringList)
                )
            }

            else -> {
                HomeUiState.Error
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