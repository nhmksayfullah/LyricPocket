package com.pocketdimen.lyricpocket.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pocketdimen.lyricpocket.data.dao.LyricDao
import com.pocketdimen.lyricpocket.data.dao.RecordingDao
import com.pocketdimen.lyricpocket.data.entity.LyricEntity
import com.pocketdimen.lyricpocket.data.entity.RecordingEntity

@Database(
    entities = [
        RecordingEntity::class,
        LyricEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class LycordDatabase: RoomDatabase() {
    abstract fun recordingDao(): RecordingDao
    abstract fun lyricDao(): LyricDao

    companion object {
        const val DATABASE_NAME = "lycord_db"
    }

}