package com.pocketdimen.lyricpocket.util

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

interface AudioPlayer {
    fun startPlaying(file: String)
    fun pausePlaying()
    fun resumePlaying()
    fun stopPlaying()

}

class LycordAudioPlayer(
    private val context: Context
): AudioPlayer {

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying: Boolean = false
    private var isPaused: Boolean = false
    private var audioFilePath: String? = null

    override fun startPlaying(filePath: String) {
        if (isPlaying) {
            stopPlaying()
        }
        audioFilePath = filePath

        mediaPlayer = MediaPlayer.create(context, File(filePath).toUri()).apply {
            start()
        }
        isPlaying = true
        isPaused = false
    }

    override fun pausePlaying() {
        if (isPlaying && !isPaused) {
            mediaPlayer?.pause()
            isPaused = true
        }
    }

    override fun resumePlaying() {
        if (isPlaying && isPaused) {
            mediaPlayer?.start()
            isPaused = false
        }
    }

    override fun stopPlaying() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
        isPlaying = false
        isPaused = false
    }
}