package com.bagreeni.fetchrewardstakehome.ui

import com.bagreeni.fetchrewardstakehome.ui.HomePage.HomeScreenViewModel
import com.bagreeni.fetchrewardstakehome.ui.HomePage.ItemData
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeScreenViewModelTest {

    private val viewModel = HomeScreenViewModel(mockk())
    //UiState Tests

    //Tests for Alphabetical sorting method.

    // properly formatted data should return sorted content based on Alphabetical sorting
    @Test
    fun testSortedAlphabeticalReturnsExpected() {
        val response =
            viewModel.hiringListToSortedUiDataAlphabetical(HomeScreenViewModelTestData.happyPathData)
        val sorted = listOf(
            ItemData(
                listId = 1,
                name = "Item 214"
            ),
            ItemData(
                listId = 1,
                name = "Item 23"
            ),
            ItemData(
                listId = 1,
                name = "Item 32"
            )
        )
        assertEquals(response, sorted)

    }

    // emptyList of data should return empty list.
    @Test
    fun testSortedAlphabeticalEmptyListReturnsExpected() {
        val response =
            viewModel.hiringListToSortedUiDataAlphabetical(HomeScreenViewModelTestData.allEmptyNameData)
        val sorted = emptyList<ItemData>()
        assertEquals(response, sorted)
    }

    // blank and null fields are sorted out appropriately
    @Test
    fun testSortedAlphabeticalNullAndEmptySortedOut() {
        val response =
            viewModel.hiringListToSortedUiDataAlphabetical(HomeScreenViewModelTestData.nullOrEmptyNameData)
        val sorted = listOf(
            ItemData(
                listId = 1,
                name = "Item 214"
            ),
            ItemData(
                listId = 1,
                name = "Item 23"
            )
        )
        assertEquals(response, sorted)
    }

    //Tests for Numerical sorting method

    // properly formatted data should return sorted content based on Numerical sorting
    @Test
    fun testSortedNumericalReturnsExpected() {
        val response =
            viewModel.hiringListToSortedUiDataNumerical(HomeScreenViewModelTestData.happyPathData)
        val sorted = listOf(
            ItemData(
                listId = 1,
                name = "Item 23"
            ),
            ItemData(
                listId = 1,
                name = "Item 32"
            ),
            ItemData(
                listId = 1,
                name = "Item 214"
            )
        )
        assertEquals(response, sorted)
    }

    // emptyList of data should return empty list.
    @Test
    fun testSortedNumericalEmptyListReturnsExpected() {
        val response =
            viewModel.hiringListToSortedUiDataNumerical(HomeScreenViewModelTestData.allEmptyNameData)
        val sorted = emptyList<ItemData>()
        assertEquals(response, sorted)
    }

    // blank and null fields are sorted out appropriately
    @Test
    fun testSortedNumericalNullAndEmptySortedOut() {
        val response =
            viewModel.hiringListToSortedUiDataNumerical(HomeScreenViewModelTestData.nullOrEmptyNameData)
        val sorted = listOf(
            ItemData(
                listId = 1,
                name = "Item 23"
            ),
            ItemData(
                listId = 1,
                name = "Item 214"
            ),
        )
        assertEquals(response, sorted)
    }

}