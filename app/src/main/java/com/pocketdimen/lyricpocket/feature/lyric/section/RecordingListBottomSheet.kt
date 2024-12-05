package com.pocketdimen.lyricpocket.feature.lyric.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.core.component.AudioItemComponent
import com.pocketdimen.lyricpocket.domain.model.Recording

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordingListBottomSheet(
    expendPlayerSheet: Boolean,
    recordings: List<Recording>,
    modifier: Modifier = Modifier,
    onPlayClick: (Recording) -> Unit,
    onDismiss: () -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState()

    if (expendPlayerSheet) {
        ModalBottomSheet(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Saved Recordings")
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(recordings) {
                        AudioItemComponent(
                            recording = it,
                            isPlaying = false,
                            onPlayClick = {
                                onPlayClick(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }


}