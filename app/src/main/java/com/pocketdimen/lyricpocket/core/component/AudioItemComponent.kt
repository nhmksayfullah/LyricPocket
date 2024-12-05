package com.pocketdimen.lyricpocket.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.theme.LyricPocketTheme
import com.pocketdimen.lyricpocket.R
import com.pocketdimen.lyricpocket.domain.model.Recording

@Composable
fun AudioItemComponent(
    recording: Recording,
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
    onPlayClick: () -> Unit = {}
) {
    Card(onClick = onPlayClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                onClick = onPlayClick

            ) {
                if(isPlaying) {
                    Icon(painter = painterResource(id = R.drawable.rounded_pause_24), contentDescription = null)
                } else {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = recording.title)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }
    }
}


@Preview
@Composable
private fun AudioItemComponentPreview() {
    LyricPocketTheme {
        AudioItemComponent(recording = Recording(
            id = "",
            lyricId = "",
            title = "Recording 2.mp3",
            filePath = ""
        ))
    }
}