package com.pocketdimen.lyricpocket.data.repo

import com.pocketdimen.lyricpocket.data.dao.RecordingDao
import com.pocketdimen.lyricpocket.data.entity.RecordingEntity
import com.pocketdimen.lyricpocket.domain.mapper.toRecording
import com.pocketdimen.lyricpocket.domain.mapper.toRecordingEntity
import com.pocketdimen.lyricpocket.domain.model.Recording
import com.pocketdimen.lyricpocket.domain.repo.RecordingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordingRepositoryImpl(
    private val dao: RecordingDao
): RecordingRepository {
    override fun getAllRecordings(): Flow<List<Recording>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecordingById(id: Int): Recording? {
        TODO("Not yet implemented")
    }

    override suspend fun insertRecording(recording: Recording) {
        dao.insertRecording(recording.toRecordingEntity())
    }

    override suspend fun deleteRecording(recording: Recording) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecording(recording: Recording) {
        TODO("Not yet implemented")
    }

    override fun getRecordingsByLyricId(lyricId: String): Flow<List<Recording>> {
        return dao.getRecordingsByLyricId(lyricId).map {
            it.map { recordingEntity: RecordingEntity ->
                recordingEntity.toRecording()
            }
        }
    }
}