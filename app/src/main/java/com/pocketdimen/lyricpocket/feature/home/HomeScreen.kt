package com.pocketdimen.lyricpocket.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pocketdimen.lyricpocket.feature.home.HomeViewModel
import com.pocketdimen.lyricpocket.core.component.LycordSearchComponent
import com.pocketdimen.lyricpocket.core.component.LyricItemComponent
import com.pocketdimen.lyricpocket.di.LycordViewModelProvider
import com.pocketdimen.lyricpocket.domain.model.Lyric
import com.pocketdimen.lyricpocket.domain.model.LyricSaver
import com.pocketdimen.lyricpocket.feature.home.section.MoreOptionBottomSheet

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = LycordViewModelProvider.Factory),
    onAddNewLyricClick: () -> Unit,
    onLyricClick: (Lyric) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        lyrics = uiState.lyrics,
        searchedLyrics = uiState.searchedLyrics,
        modifier = modifier,
        onAddNewLyricClick = onAddNewLyricClick,
        onLyricClick = onLyricClick,
        onQueryChange = {
            viewModel.onEvent(HomeUiEvent.OnQueryChange(it))
        },
        onDeleteLyric = {
            viewModel.onEvent(HomeUiEvent.OnDeleteLyric(it))
        }
    )
}

@Composable
fun HomeScreenContent(
    lyrics: List<Lyric>,
    searchedLyrics: List<Lyric>,
    modifier: Modifier = Modifier,
    onAddNewLyricClick: () -> Unit,
    onLyricClick: (Lyric) -> Unit,
    onQueryChange: (String) -> Unit = {},
    onDeleteLyric: (Lyric) -> Unit = {}
) {

    var expandMoreSheet by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedLyric: Lyric? by rememberSaveable(stateSaver = LyricSaver) {
        mutableStateOf(null)
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNewLyricClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        topBar = {
            LycordSearchComponent(
                onQueryChange = {
                    onQueryChange(it)
                },
                onSearch = {
                    onQueryChange(it)
                },
                content = {
                    SearchResultContent(
                        lyrics = searchedLyrics,
                        onLyricClick = onLyricClick
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            if (lyrics.isNotEmpty()) {
                LazyColumn {
                    items(lyrics) {
                        LyricItemComponent(
                            lyric = it,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            onClick = {
                                onLyricClick(it)

                            },
                            onMoreClick = {
                                expandMoreSheet = true
                                selectedLyric = it
                            }
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No Lyric Available")
                }
            }

            if (expandMoreSheet) {
                MoreOptionBottomSheet(
                    isExpanded = expandMoreSheet,
                    onDismiss = {
                        expandMoreSheet = false
                    },
                    onDelete = {
                        selectedLyric?.let {
                            onDeleteLyric(it)
                            selectedLyric = null
                        }
                        expandMoreSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun SearchResultContent(
    lyrics: List<Lyric>,
    modifier: Modifier = Modifier,
    onLyricClick: (Lyric) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(lyrics) {
            LyricItemComponent(lyric = it) {
                onLyricClick(it)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}