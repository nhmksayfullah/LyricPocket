package com.pocketdimen.lyricpocket.feature.lyric.event

sealed class LyricUiEvent {
    data class OnPopulate(val id: String, val title: String, val content: String): LyricUiEvent()
    data class OnTitleChange(val title: String): LyricUiEvent()
    data class OnContentChange(val content: String): LyricUiEvent()
    data object OnSaveClick: LyricUiEvent()
    data object OnEditClick: LyricUiEvent()
}