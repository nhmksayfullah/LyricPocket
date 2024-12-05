package com.pocketdimen.lyricpocket.domain.mapper

import com.pocketdimen.lyricpocket.data.entity.LyricEntity
import com.pocketdimen.lyricpocket.domain.model.Lyric

fun Lyric.toLyricEntity(): LyricEntity {
    return LyricEntity(
        id = id,
        title =  title,
        content = content
    )
}

fun LyricEntity.toLyric(): Lyric {
    return Lyric(
        id = id,
        title = title,
        content = content
    )
}