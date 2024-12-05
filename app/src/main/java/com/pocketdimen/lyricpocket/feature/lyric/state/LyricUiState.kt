package com.pocketdimen.lyricpocket.feature.lyric.state

data class LyricUiState(
    val currentId: String? = null,
    val currentTitle: String = "",
    val currentContent: String = "",
    val readOnly: Boolean = currentId != null
)