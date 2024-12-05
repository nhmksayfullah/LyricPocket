package com.pocketdimen.lyricpocket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordingEntity(
    @PrimaryKey
    val id: String,
    val lyricId: String,
    val title: String,
    val filePath: String
)
