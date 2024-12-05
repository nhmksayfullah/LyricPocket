package com.pocketdimen.lyricpocket.feature.lyric.state

data class AppUiState(
    val bottomBar: BottomBar = BottomBar.MainBottomBar,
)


enum class BottomBar {
    MainBottomBar, RecorderBottomBar, PlayerBottomBar
}