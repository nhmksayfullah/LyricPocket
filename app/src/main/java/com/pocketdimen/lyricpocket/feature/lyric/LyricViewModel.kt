package com.pocketdimen.lyricpocket.feature.lyric

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocketdimen.lyricpocket.domain.model.Lyric
import com.pocketdimen.lyricpocket.domain.model.Recording
import com.pocketdimen.lyricpocket.domain.repo.LyricRepository
import com.pocketdimen.lyricpocket.domain.repo.RecordingRepository
import com.pocketdimen.lyricpocket.feature.lyric.event.AppUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.event.LyricUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.state.LyricUiState
import com.pocketdimen.lyricpocket.feature.lyric.event.AudioPlayerUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.state.PlayerUiState
import com.pocketdimen.lyricpocket.feature.lyric.event.AudioRecorderUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.state.AppUiState
import com.pocketdimen.lyricpocket.feature.lyric.state.RecorderUiState
import com.pocketdimen.lyricpocket.util.AudioPlayer
import com.pocketdimen.lyricpocket.util.AudioRecorder
import com.pocketdimen.lyricpocket.util.Constant
import com.pocketdimen.lyricpocket.util.generateUniqueId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LyricViewModel(
    private val lyricRepository: LyricRepository,
    private val recordingRepository: RecordingRepository,
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer
): ViewModel() {

    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState = _appUiState.asStateFlow()

    private val _lyricUiState = MutableStateFlow(LyricUiState())
    val lyricUiState = _lyricUiState.asStateFlow()

    private val _recorderUiState = MutableStateFlow(RecorderUiState())
    val recorderUiState = _recorderUiState.asStateFlow()

    private val _playerUiState = MutableStateFlow(PlayerUiState())
    val playerUiState = _playerUiState.asStateFlow()


    fun onEvent(event: AppUiEvent) {
        when(event) {
            is AppUiEvent.OnChangeBottomBar -> {
                _appUiState.update {
                    it.copy(
                        bottomBar = event.bottomBar
                    )
                }
            }
        }
    }

    fun onEvent(event: LyricUiEvent) {
        when(event) {
            is LyricUiEvent.OnContentChange -> {
                _lyricUiState.update { currentState ->
                    currentState.copy(
                        currentContent = event.content
                    )
                }
            }
            LyricUiEvent.OnSaveClick -> {
                if (lyricUiState.value.currentTitle.isNotBlank() && lyricUiState.value.currentContent.isNotBlank()) {
                    viewModelScope.launch {


                        if (lyricUiState.value.currentId == null) {
                            val id = generateUniqueId()
                            lyricRepository.upsertLyric(
                                Lyric(
                                    id = id,
                                    title = lyricUiState.value.currentTitle,
                                    content = lyricUiState.value.currentContent
                                )
                            )
                            _lyricUiState.update { currentState ->
                                currentState.copy(
                                    currentId = id,
                                    readOnly = true
                                )
                            }
                        } else {
                            lyricRepository.upsertLyric(
                                Lyric(
                                    id = lyricUiState.value.currentId!!,
                                    title = lyricUiState.value.currentTitle,
                                    content = lyricUiState.value.currentContent
                                )
                            )
                            _lyricUiState.update { currentState ->
                                currentState.copy(
                                    readOnly = true
                                )
                            }
                        }

                    }
                }
            }
            is LyricUiEvent.OnTitleChange -> {
                _lyricUiState.update { currentState ->
                    currentState.copy(
                        currentTitle = event.title
                    )
                }
            }

            LyricUiEvent.OnEditClick -> {
                _lyricUiState.update { currentState ->
                    currentState.copy(readOnly = false)
                }
            }

            is LyricUiEvent.OnPopulate -> {
                _lyricUiState.update { currentState ->
                    currentState.copy(
                        currentId = event.id,
                        currentTitle = event.title,
                        currentContent = event.content,
                        readOnly = true
                    )
                }
                populateRecordings()
            }
        }

    }

    fun onEvent(event: AudioRecorderUiEvent) {
        when (event) {
            AudioRecorderUiEvent.OnStartRecording -> startRecording()
            AudioRecorderUiEvent.OnPauseRecording -> pauseRecording()
            AudioRecorderUiEvent.OnResumeRecording -> resumeRecording()
            AudioRecorderUiEvent.OnCancelRecording -> cancelRecording()
            AudioRecorderUiEvent.OnSaveRecording -> saveRecording()
        }
    }


    fun onEvent(event: AudioPlayerUiEvent) {
        when(event) {
            is AudioPlayerUiEvent.OnPlayRecording -> playRecording(event.recording)
            AudioPlayerUiEvent.OnPlayPause -> pauseResumeRecording()

            AudioPlayerUiEvent.OnStopPlaying -> stopPlaying()
        }
    }


    private fun populateRecordings() {
        Log.d("LyricViewModel", "${lyricUiState.value.currentId}")
        lyricUiState.value.currentId?.let {
            recordingRepository.getRecordingsByLyricId(it).onEach {
                _playerUiState.update { currentState ->
                    currentState.copy(
                        recordings = it
                    )
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun playRecording(recording: Recording) {
        when {
            recording == playerUiState.value.currentRecording -> {
                return
            }
            else -> {
                _playerUiState.update {
                    it.copy(
                        currentRecording = recording,
                        isPlaying = true,
                        isPaused = false
                    )
                }
                audioPlayer.stopPlaying()
                audioPlayer.startPlaying(recording.filePath)
            }
        }
    }

    private fun pauseResumeRecording() {
        if (playerUiState.value.isPaused == false) {
            audioPlayer.pausePlaying()
            _playerUiState.update {
                it.copy(
                    isPaused = true
                )
            }
        } else if (playerUiState.value.isPaused == true) {
            audioPlayer.resumePlaying()
            _playerUiState.update {
                it.copy(
                    isPaused = false
                )
            }
        }
    }
    private fun stopPlaying() {
        audioPlayer.stopPlaying()
        _playerUiState.update {
            it.copy(
                isPlaying = false,
                isPaused = null
            )
        }
    }


    private fun startRecording() {
        if (lyricUiState.value.currentId == null) {
            _lyricUiState.update { currentState ->
                currentState.copy(
                    currentId = generateUniqueId()
                )
            }
        }
        viewModelScope.launch {
            val fileName = audioRecorder.startRecording()
            _recorderUiState.update { currentState ->
                currentState.copy(
                    title = fileName,
                    isRecording = true
                )
            }
        }
    }

    private fun pauseRecording() {
        viewModelScope.launch {
            audioRecorder.pauseRecording()
            _recorderUiState.update { currentState ->
                currentState.copy(
                    isRecording = false
                )
            }
        }
    }

    private fun resumeRecording() {
        viewModelScope.launch {
            audioRecorder.resumeRecording()
            _recorderUiState.update { currentState ->
                currentState.copy(
                    isRecording = true
                )
            }
        }
    }

    private fun cancelRecording() {
        viewModelScope.launch {
            audioRecorder.cancelRecording()
            _recorderUiState.update { currentState ->
                currentState.copy(
                    id = null,
                    isRecording = null,
                    filePath = null,
                    title = ""
                )
            }
        }
    }

    private fun saveRecording() {
        viewModelScope.launch {
            audioRecorder.saveRecording()?.let { filePath ->
                recordingRepository.insertRecording(
                    Recording(
                        id = generateUniqueId(),
                        lyricId = lyricUiState.value.currentId ?: Constant.NoLyricFound,
                        title = recorderUiState.value.title,
                        filePath = filePath
                    )
                )

                _recorderUiState.update {
                    it.copy(
                        isRecording = null,
                        title = "",
                        filePath = null,
                        id = null
                    )
                }

            }
            if (playerUiState.value.recordings.isEmpty()) {
                populateRecordings()
            }
        }
    }
}