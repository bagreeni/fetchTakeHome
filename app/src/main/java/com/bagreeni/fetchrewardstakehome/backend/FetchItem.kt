package com.bagreeni.fetchrewardstakehome.backend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchItem(
    @SerialName("id") val id: Int,
    @SerialName("listId") val listId: Int,
    @SerialName("name") val name: String? = ""
)