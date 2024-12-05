package com.pocketdimen.lyricpocket.domain.model

import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Serializable

@Serializable
data class Lyric(
    val id: String,
    val title: String,
    val content: String
)


val LyricSaver = Saver<Lyric?, List<String?>>(
    save = { lyric: Lyric? ->
        lyric?.let {
            listOf(lyric.id, lyric.title, lyric.content)
        }
    },
    restore = { list ->
        list.let {
            Lyric(id = it[0] ?: "", title = it[1] ?: "", content = it[2] ?: "")
        }
    }
)

