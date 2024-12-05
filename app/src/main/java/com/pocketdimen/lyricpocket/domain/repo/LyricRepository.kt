package com.pocketdimen.lyricpocket.domain.repo

import com.pocketdimen.lyricpocket.domain.model.Lyric
import kotlinx.coroutines.flow.Flow

interface LyricRepository {
    fun getAllLyric(): Flow<List<Lyric>>
    suspend fun upsertLyric(lyric: Lyric)
    suspend fun deleteLyric(lyric: Lyric)
    fun getLyricById(id: Int): Flow<Lyric?>
    fun searchLyric(query: String): Flow<List<Lyric>>
}