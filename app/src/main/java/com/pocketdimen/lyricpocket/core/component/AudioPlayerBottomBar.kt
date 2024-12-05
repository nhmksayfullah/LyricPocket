package com.pocketdimen.lyricpocket.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.R
import com.pocketdimen.lyricpocket.theme.LyricPocketTheme

@Composable
fun AudioPlayerBottomBar(
    isPaused: Boolean?,
    modifier: Modifier = Modifier,
    onPlayPauseClick: () -> Unit,
    onDismiss: () -> Unit
) {
    BottomAppBar {

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )  {
                IconButton(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                    onClick = onPlayPauseClick
                ) {
                    if (isPaused == false) {
                        Icon(painter = painterResource(id = R.drawable.rounded_pause_24), contentDescription = null)
                    } else if (isPaused == true) {
                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    }
                }
            }
            IconButton(onClick = onDismiss) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
private fun AudioPlayerBottomBarPreview() {
    LyricPocketTheme {
        AudioPlayerBottomBar(isPaused = false, onPlayPauseClick = {}, onDismiss = {})
    }
}