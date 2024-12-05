package com.pocketdimen.lyricpocket.core.component


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketdimen.lyricpocket.theme.LyricPocketTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LycordSearchComponent(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    content: @Composable () -> Unit
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .semantics { isTraversalGroup = true }
        .padding(horizontal = if (expanded) 0.dp else 16.dp, vertical = 8.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .semantics { traversalIndex = 0f }
            ,
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = {
                        query = it
                        onQueryChange(it)
                    },
                    onSearch = {
                        onSearch(query)
//                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = {
                        Text(text = "Search Lyrics")
                    },
                    leadingIcon = {
                        if (expanded) {
                            IconButton(onClick = {
                                query = ""
                                onQueryChange("")
                                expanded = false
                            }) {
                                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        } else {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            content()
        }
    }

}

@Preview
@Composable
private fun SearchComponentPreview() {
    LyricPocketTheme {
        LycordSearchComponent{
            Text(text = "Hello")
        }
    }
}