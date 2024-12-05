package com.pocketdimen.lyricpocket.feature.lyric.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.R

// this is the bottom sheet that is responsible for recording the audio
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecorderBottomSheet(
    expendRecordSheet: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onRecordClick: () -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState()
    if (expendRecordSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Start Recording",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // This is the record button
                    IconButton(
                        modifier = Modifier
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.errorContainer, CircleShape),
                        onClick = onRecordClick
                    ) {
                    }
                }
            }
        }
    }
}
