package com.pocketdimen.lyricpocket.navigation

import com.pocketdimen.lyricpocket.domain.model.Lyric
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Serializable
data class AddEditLyricScreenRoute(
    val id: String? = null,
    val title: String = "",
    val content: String = ""
)

