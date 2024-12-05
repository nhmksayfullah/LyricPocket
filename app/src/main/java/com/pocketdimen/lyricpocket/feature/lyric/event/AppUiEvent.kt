package com.pocketdimen.lyricpocket.feature.lyric.event

import com.pocketdimen.lyricpocket.feature.lyric.state.BottomBar

sealed class AppUiEvent {
    data class OnChangeBottomBar(val bottomBar: BottomBar): AppUiEvent()
}