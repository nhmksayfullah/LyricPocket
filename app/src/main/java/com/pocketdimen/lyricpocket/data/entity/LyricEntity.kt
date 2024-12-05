package com.pocketdimen.lyricpocket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LyricEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String
)
