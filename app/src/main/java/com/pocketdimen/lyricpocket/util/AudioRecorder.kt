package com.pocketdimen.lyricpocket.util

import android.content.Context
import android.media.MediaRecorder
import java.io.File

interface AudioRecorder {
    fun startRecording(): String
    fun pauseRecording()
    fun resumeRecording()
    fun cancelRecording()
    fun saveRecording(): String?
}

class LycordAudioRecorder(
    private val context: Context
): AudioRecorder {

    private var mediaRecorder: MediaRecorder? = null
    private var audioFilePath: String? = null
    private var isRecording: Boolean = false
    private var isPaused: Boolean = false


    private fun createRecorder(): MediaRecorder {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }

    override fun startRecording(): String {
        if (isRecording) {
            stopRecording()
        }
        val fileName = "${System.currentTimeMillis()}.mp3"
        audioFilePath = "${context.filesDir.absolutePath}/$fileName"

        mediaRecorder = createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(audioFilePath)
            prepare()
            start()
        }
        isRecording = true
        isPaused = false
        return fileName
    }

    override fun pauseRecording() {
        if (isRecording && !isPaused) {
            mediaRecorder?.pause()
            isPaused = true
        }
    }

    override fun resumeRecording() {
        if (isRecording && isPaused) {
            mediaRecorder?.resume()
            isPaused = false
        }
    }

    override fun cancelRecording() {
        mediaRecorder?.apply {
            stop()
            reset()
            release()
        }
        mediaRecorder = null
        isRecording = false
        isPaused = false
        audioFilePath?.let { File(it).delete() }
        audioFilePath = null
    }

    override fun saveRecording(): String? {
        if (isRecording) {
            mediaRecorder?.apply {
                stop()
                reset()
                release()
            }
            mediaRecorder = null
            isRecording = false
            isPaused = false
            return audioFilePath
        }
        return null
    }

    private fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            reset()
            release()
        }
        mediaRecorder = null
        isRecording = false
        isPaused = false
    }

}