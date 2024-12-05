package com.pocketdimen.lyricpocket.domain.repo

import com.pocketdimen.lyricpocket.domain.model.Recording
import kotlinx.coroutines.flow.Flow

interface RecordingRepository {
    fun getAllRecordings(): Flow<List<Recording>>
    suspend fun getRecordingById(id: Int): Recording?
    suspend fun insertRecording(recording: Recording)
    suspend fun deleteRecording(recording: Recording)
    suspend fun updateRecording(recording: Recording)
    fun getRecordingsByLyricId(lyricId: String): Flow<List<Recording>>
}