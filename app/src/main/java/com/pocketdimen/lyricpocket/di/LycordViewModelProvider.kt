package com.pocketdimen.lyricpocket.di

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pocketdimen.lyricpocket.navigation.LycordApplication
import com.pocketdimen.lyricpocket.feature.lyric.LyricViewModel
import com.pocketdimen.lyricpocket.feature.home.HomeViewModel

object LycordViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(LycordApplication.appModule.lyricRepository)
        }

        initializer {
            LyricViewModel(
                LycordApplication.appModule.lyricRepository,
                LycordApplication.appModule.recordingRepository,
                LycordApplication.appModule.audioRecorder,
                LycordApplication.appModule.audioPlayer
            )
        }

    }
}