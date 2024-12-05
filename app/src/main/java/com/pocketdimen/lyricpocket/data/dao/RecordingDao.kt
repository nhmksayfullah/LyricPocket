package com.pocketdimen.lyricpocket.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pocketdimen.lyricpocket.data.entity.RecordingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordingDao {

    @Query("SELECT * FROM recordingentity")
    fun getAllRecordings(): Flow<List<RecordingEntity>>

    @Insert
    suspend fun insertRecording(recording: RecordingEntity)

    @Update
    suspend fun updateRecording(recording: RecordingEntity)

    @Delete
    suspend fun deleteRecording(recording: RecordingEntity)

    @Query("SELECT * FROM recordingentity WHERE id = :id")
    fun getRecordingById(id: Int): Flow<RecordingEntity>

    @Query("SELECT * FROM recordingentity WHERE lyricId = :lyricId")
    fun getRecordingsByLyricId(lyricId: String): Flow<List<RecordingEntity>>

}