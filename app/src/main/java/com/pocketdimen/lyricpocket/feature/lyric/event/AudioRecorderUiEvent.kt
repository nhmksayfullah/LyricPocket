package com.pocketdimen.lyricpocket.feature.lyric.event

sealed class AudioRecorderUiEvent {
    data object OnStartRecording: AudioRecorderUiEvent()
    data object OnPauseRecording: AudioRecorderUiEvent()
    data object OnResumeRecording: AudioRecorderUiEvent()
    data object OnCancelRecording: AudioRecorderUiEvent()
    data object OnSaveRecording: AudioRecorderUiEvent()
}