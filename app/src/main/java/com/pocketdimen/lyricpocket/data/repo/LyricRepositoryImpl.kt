package com.pocketdimen.lyricpocket.data.repo

import com.pocketdimen.lyricpocket.data.dao.LyricDao
import com.pocketdimen.lyricpocket.domain.mapper.toLyric
import com.pocketdimen.lyricpocket.domain.mapper.toLyricEntity
import com.pocketdimen.lyricpocket.domain.model.Lyric
import com.pocketdimen.lyricpocket.domain.repo.LyricRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LyricRepositoryImpl(
    private val dao: LyricDao
): LyricRepository {
    override fun getAllLyric(): Flow<List<Lyric>> {
        return dao.getAllLyrics().map {
            it.map { it.toLyric() }
        }
    }

    override suspend fun upsertLyric(lyric: Lyric) {
        dao.upsertLyric(lyric.toLyricEntity())
    }

    override suspend fun deleteLyric(lyric: Lyric) {
        dao.deleteLyric(lyric.toLyricEntity())
    }

    override fun getLyricById(id: Int): Flow<Lyric?> {
        return dao.getLyricById(id).map {
            it.toLyric()
        }
    }

    override fun searchLyric(query: String): Flow<List<Lyric>> {
        return dao.searchLyrics(query).map {
            it.map { it.toLyric() }
        }
    }
}