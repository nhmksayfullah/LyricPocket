package com.pocketdimen.lyricpocket.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocketdimen.lyricpocket.domain.repo.LyricRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val lyricRepository: LyricRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        lyricRepository.getAllLyric().onEach {
            _uiState.update { currentState ->
                currentState.copy(
                    lyrics = it
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeUiEvent) {
        when(event) {
            is HomeUiEvent.OnQueryChange -> {
                _uiState.update {
                    it.copy(
                        searchedLyrics = emptyList()
                    )
                }
                if(event.query.isBlank()) return
                lyricRepository.searchLyric(event.query).onEach {

                    _uiState.update {
                        currentState -> currentState.copy(
                            searchedLyrics = it
                        )
                    }
                }.launchIn(viewModelScope)
            }

            is HomeUiEvent.OnDeleteLyric -> {
                viewModelScope.launch {
                    lyricRepository.deleteLyric(event.lyric)
                }
            }
        }
    }

}