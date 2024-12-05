package com.pocketdimen.lyricpocket.feature.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.theme.LyricPocketTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreOptionBottomSheet(
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    if (isExpanded) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Row(
                    modifier = modifier
                        .clickable {
                            onDelete()
                        }
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = "Delete")
                }
            }
        }
    }
}

