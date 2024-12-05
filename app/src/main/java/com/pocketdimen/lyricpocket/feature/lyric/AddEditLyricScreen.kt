package com.pocketdimen.lyricpocket.feature.lyric

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pocketdimen.lyricpocket.core.component.AudioPlayerBottomBar
import com.pocketdimen.lyricpocket.core.component.RecorderBottomBar
import com.pocketdimen.lyricpocket.feature.lyric.event.AppUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.event.LyricUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.event.AudioPlayerUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.section.AddEditLyricScreenBottomBar
import com.pocketdimen.lyricpocket.feature.lyric.section.RecorderBottomSheet
import com.pocketdimen.lyricpocket.feature.lyric.section.RecordingListBottomSheet
import com.pocketdimen.lyricpocket.feature.lyric.event.AudioRecorderUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.state.BottomBar

@Composable
fun AddEditLyricScreen(
    modifier: Modifier = Modifier,
    viewModel: LyricViewModel,
    onMenuClick: (String?) -> Unit
) {
    val appUiState by viewModel.appUiState.collectAsStateWithLifecycle()
    val lyricUiState by viewModel.lyricUiState.collectAsStateWithLifecycle()
    val recorderUiState by viewModel.recorderUiState.collectAsStateWithLifecycle()
    val audioPlayerUiState by viewModel.playerUiState.collectAsStateWithLifecycle()

    var expendRecordSheet by rememberSaveable {
        mutableStateOf(false)
    }
    var expendPlayerSheet by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            // This is the main content of the screen
            AddEditLyricScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                currentTitle = lyricUiState.currentTitle,
                currentContent = lyricUiState.currentContent,
                readOnly = lyricUiState.readOnly,
                onTitleChange = {
                    viewModel.onEvent(LyricUiEvent.OnTitleChange(it))
                },
                onContentChange = {
                    viewModel.onEvent(LyricUiEvent.OnContentChange(it))
                }
            )

            // This Bottom sheet is responsible for recording the audio
            RecorderBottomSheet(
                expendRecordSheet = expendRecordSheet,
                onDismiss = {
                    expendRecordSheet = false
                },
                onRecordClick = {
                    viewModel.onEvent(AudioRecorderUiEvent.OnStartRecording)
                    viewModel.onEvent(AppUiEvent.OnChangeBottomBar(BottomBar.RecorderBottomBar))
                    expendRecordSheet = false
                }
            )

            RecordingListBottomSheet(
                expendPlayerSheet = expendPlayerSheet,
                recordings = audioPlayerUiState.recordings,
                onPlayClick = {
                    viewModel.onEvent(AudioPlayerUiEvent.OnPlayRecording(it))
                    viewModel.onEvent(AppUiEvent.OnChangeBottomBar(BottomBar.PlayerBottomBar))
                    expendPlayerSheet = false
                },
                onDismiss = {
                    expendPlayerSheet = false
                }
            )
        },
        bottomBar = {
            // This is the bottom bar of the screen
            when(appUiState.bottomBar) {
                BottomBar.MainBottomBar -> {
                    AddEditLyricScreenBottomBar(
                        readOnly = lyricUiState.readOnly,
                        onExpandRecordSheet = {
                            expendRecordSheet = true
                        },
                        onExpandPlayerSheet = {
                            expendPlayerSheet = true
                        },
                        onEditLyricClick = {
                            viewModel.onEvent(LyricUiEvent.OnEditClick)
                        },
                        onSaveLyricClick = {
                            viewModel.onEvent(LyricUiEvent.OnSaveClick)
                        }
                    )
                }
                BottomBar.RecorderBottomBar -> {
                    RecorderBottomBar(
                        isRecording = recorderUiState.isRecording,
                        onRecordClick = {
                            when(recorderUiState.isRecording) {
                                true -> viewModel.onEvent(AudioRecorderUiEvent.OnPauseRecording)
                                false -> viewModel.onEvent(AudioRecorderUiEvent.OnResumeRecording)
                                null -> viewModel.onEvent(AudioRecorderUiEvent.OnStartRecording)
                            }
                        },
                        onDismiss = {
                            viewModel.onEvent(AudioRecorderUiEvent.OnCancelRecording)
                            viewModel.onEvent(AppUiEvent.OnChangeBottomBar(BottomBar.MainBottomBar))
                        },
                        onSavedClick = {
                            viewModel.onEvent(AudioRecorderUiEvent.OnSaveRecording)
                            viewModel.onEvent(AppUiEvent.OnChangeBottomBar(BottomBar.MainBottomBar))
                        }
                    )
                }
                BottomBar.PlayerBottomBar -> {
                    AudioPlayerBottomBar(
                        isPaused = audioPlayerUiState.isPaused,
                        onPlayPauseClick = {
                            viewModel.onEvent(AudioPlayerUiEvent.OnPlayPause)
                        },
                        onDismiss = {
                            viewModel.onEvent(AppUiEvent.OnChangeBottomBar(BottomBar.MainBottomBar))
                            viewModel.onEvent(AudioPlayerUiEvent.OnStopPlaying)
                        }
                    )
                }
            }
        }
    )
}



// This is the main content of the screen
@Composable
fun AddEditLyricScreenContent(
    currentTitle: String,
    currentContent: String,
    readOnly: Boolean,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = currentTitle,
            onValueChange = onTitleChange,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textStyle = MaterialTheme.typography.titleLarge,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            readOnly = readOnly,
            placeholder = {
                Text(text = "Title")
            }
        )
        TextField(
            value = currentContent,
            onValueChange = onContentChange,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            readOnly = readOnly,
            placeholder = {
                Text(text = "Lyric")
            }
        )
    }
}




