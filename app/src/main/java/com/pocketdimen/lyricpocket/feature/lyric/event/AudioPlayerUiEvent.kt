package com.pocketdimen.lyricpocket.feature.lyric.event

import com.pocketdimen.lyricpocket.domain.model.Recording

sealed class AudioPlayerUiEvent {
    data class OnPlayRecording(val recording: Recording): AudioPlayerUiEvent()
    data object OnPlayPause: AudioPlayerUiEvent()
    data object OnStopPlaying: AudioPlayerUiEvent()
//    object SeekToNext: PlayerUiEvent()
//    object SeekToPrevious: PlayerUiEvent()
}