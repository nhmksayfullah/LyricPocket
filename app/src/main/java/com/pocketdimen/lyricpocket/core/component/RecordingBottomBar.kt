package com.pocketdimen.lyricpocket.core.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.R
import com.pocketdimen.lyricpocket.theme.LyricPocketTheme

@Composable
fun RecorderBottomBar(
    isRecording: Boolean?,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onRecordClick: () -> Unit,
    onSavedClick: () -> Unit
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth().then(modifier),
            horizontalArrangement = Arrangement.Center
        ) {

            // This is the close button
            TextButton(onClick = onDismiss) {
                when(isRecording) {
                    true -> Text(text = "Cancel")
                    false -> Text(text = "Delete")
                    null -> {}
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // This is the record button
            IconButton(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.errorContainer, CircleShape),
                onClick = onRecordClick
            ) {
                when(isRecording) {
                    true -> Icon(painter = painterResource(id = R.drawable.rounded_pause_24), contentDescription = null)
                    false -> Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    null -> {}
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // This is the menu button to open the saved recordings
            TextButton(onClick = onSavedClick) {
                Text(text = "Save")
            }
        }
    }
}

@Preview
@Composable
private fun RecorderBottomBarPreview() {
    LyricPocketTheme {
        RecorderBottomBar(isRecording = true, onDismiss = { /*TODO*/ }, onRecordClick = { /*TODO*/ }) {

        }
    }
}