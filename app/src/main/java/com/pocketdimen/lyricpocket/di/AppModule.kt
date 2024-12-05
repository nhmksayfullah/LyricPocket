package com.pocketdimen.lyricpocket.di

import android.content.Context
import androidx.room.Room
import com.pocketdimen.lyricpocket.data.LycordDatabase
import com.pocketdimen.lyricpocket.data.repo.LyricRepositoryImpl
import com.pocketdimen.lyricpocket.data.repo.RecordingRepositoryImpl
import com.pocketdimen.lyricpocket.domain.repo.LyricRepository
import com.pocketdimen.lyricpocket.domain.repo.RecordingRepository
import com.pocketdimen.lyricpocket.util.AudioPlayer
import com.pocketdimen.lyricpocket.util.AudioRecorder
import com.pocketdimen.lyricpocket.util.LycordAudioPlayer
import com.pocketdimen.lyricpocket.util.LycordAudioRecorder

class AppModuleImpl(
    private val context: Context
): AppModule {
    override val roomDatabase: LycordDatabase by lazy {
        Room.databaseBuilder(
            context,
            LycordDatabase::class.java,
            LycordDatabase.DATABASE_NAME
        ).build()
    }
    override val lyricRepository: LyricRepository by lazy {
        LyricRepositoryImpl(roomDatabase.lyricDao())
    }
    override val recordingRepository: RecordingRepository by lazy {
        RecordingRepositoryImpl(roomDatabase.recordingDao())
    }
    override val audioRecorder: AudioRecorder by lazy {
        LycordAudioRecorder(context)
    }
    override val audioPlayer: AudioPlayer by lazy {
        LycordAudioPlayer(context)
    }

}

interface AppModule {
    val roomDatabase: LycordDatabase
    val lyricRepository: LyricRepository
    val recordingRepository: RecordingRepository
    val audioRecorder: AudioRecorder
    val audioPlayer: AudioPlayer

}