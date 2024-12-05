package com.pocketdimen.lyricpocket.feature.home

import com.pocketdimen.lyricpocket.domain.model.Lyric

sealed class HomeUiEvent {
    data class OnQueryChange(val query: String) : HomeUiEvent()
    data class OnDeleteLyric(val lyric: Lyric) : HomeUiEvent()
}