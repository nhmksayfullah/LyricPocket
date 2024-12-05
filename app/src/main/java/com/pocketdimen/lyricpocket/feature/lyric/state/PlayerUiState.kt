package com.pocketdimen.lyricpocket.feature.lyric.state

import com.pocketdimen.lyricpocket.domain.model.Recording


data class PlayerUiState(
    val recordings: List<Recording> = emptyList(),
    val currentRecording: Recording? = null,
    val isPlaying: Boolean = false,
    val isPaused: Boolean? = null
)