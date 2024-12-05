package com.pocketdimen.lyricpocket.feature.home

import com.pocketdimen.lyricpocket.domain.model.Lyric

data class HomeUiState(
    val lyrics: List<Lyric> = emptyList(),
    val searchedLyrics: List<Lyric> = emptyList(),
)
