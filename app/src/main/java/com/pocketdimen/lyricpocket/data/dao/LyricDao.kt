package com.pocketdimen.lyricpocket.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.pocketdimen.lyricpocket.data.entity.LyricEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LyricDao {

    @Query("SELECT * FROM lyricentity")
    fun getAllLyrics(): Flow<List<LyricEntity>>

    @Query("SELECT * FROM lyricentity WHERE id = :id")
    fun getLyricById(id: Int): Flow<LyricEntity>

    @Upsert
    suspend fun upsertLyric(lyric: LyricEntity)

    @Delete
    suspend fun deleteLyric(lyric: LyricEntity)

    @Query("SELECT * FROM lyricentity WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchLyrics(query: String): Flow<List<LyricEntity>>


}