package com.bagreeni.fetchrewardstakehome.ui

import com.bagreeni.fetchrewardstakehome.backend.FetchItem

object HomeScreenViewModelTestData {
val happyPathData = listOf(
    FetchItem(id = 12,
    listId = 1,
        name = ""),
    FetchItem(id = 2,
        listId = 1,
        name = "Item 23"),
    FetchItem(id = 5,
        listId = 1,
        name = "Item 214"),
    FetchItem(id = 1,
        listId = 1,
        name = "Item 32"),
)

    val nullOrEmptyNameData = listOf(
        FetchItem(id = 12,
            listId = 1,
            name = null),
        FetchItem(id = 2,
            listId = 1,
            name = "Item 23"),
        FetchItem(id = 5,
            listId = 1,
            name = "Item 214"),
        FetchItem(id = 1,
            listId = 1,
            name = ""),
    )

    val allEmptyNameData = listOf(
        FetchItem(id = 12,
            listId = 1,
            name = null),
        FetchItem(id = 2,
            listId = 2,
            name = ""),
        FetchItem(id = 5,
            listId = 1,
            name = ""),
        FetchItem(id = 1,
            listId = 1,
            name = ""),
    )
}