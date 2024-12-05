package com.pocketdimen.lyricpocket.feature.lyric.state

data class RecorderUiState(
    val id: String? = null,
    val title: String = "",
    val filePath: String? = null,
    val isRecording: Boolean? = null
)
