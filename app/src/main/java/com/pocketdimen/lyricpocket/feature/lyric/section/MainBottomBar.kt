package com.pocketdimen.lyricpocket.feature.lyric.section

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pocketdimen.lyricpocket.R
import com.pocketdimen.lyricpocket.theme.LyricPocketTheme

@Composable
fun AddEditLyricScreenBottomBar(
    readOnly: Boolean,
    modifier: Modifier = Modifier,
    onExpandRecordSheet: () -> Unit,
    onExpandPlayerSheet: () -> Unit,
    onEditLyricClick: () -> Unit,
    onSaveLyricClick: () -> Unit,
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = onExpandRecordSheet) {
                Icon(painter = painterResource(id = R.drawable.rounded_mic_24), contentDescription = null)
            }
            IconButton(onClick = onExpandPlayerSheet) {
                Icon(painter = painterResource(id = R.drawable.rounded_headphones_24), contentDescription = null)
            }
        },
        floatingActionButton = {
            if (readOnly) {
                FloatingActionButton(onClick = onEditLyricClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
            } else {
                FloatingActionButton(onClick = onSaveLyricClick) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = null)
                }
            }
        }
    )
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    LyricPocketTheme {
        AddEditLyricScreenBottomBar(
            readOnly = true,
            onExpandRecordSheet = { /*TODO*/ },
            onExpandPlayerSheet = { /*TODO*/ },
            onEditLyricClick = { /*TODO*/ }) {
        }
    }
}