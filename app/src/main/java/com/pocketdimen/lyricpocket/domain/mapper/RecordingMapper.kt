package com.pocketdimen.lyricpocket.domain.mapper

import com.pocketdimen.lyricpocket.data.entity.RecordingEntity
import com.pocketdimen.lyricpocket.domain.model.Recording

fun Recording.toRecordingEntity(): RecordingEntity {
    return RecordingEntity(
        id = id,
        lyricId = lyricId,
        title = title,
        filePath = filePath
    )
}

fun RecordingEntity.toRecording(): Recording {
    return Recording(
        id = id,
        lyricId = lyricId,
        title = title,
        filePath = filePath
    )
}