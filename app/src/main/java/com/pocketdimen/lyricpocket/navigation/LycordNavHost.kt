package com.pocketdimen.lyricpocket.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pocketdimen.lyricpocket.di.LycordViewModelProvider
import com.pocketdimen.lyricpocket.feature.lyric.AddEditLyricScreen
import com.pocketdimen.lyricpocket.feature.lyric.event.LyricUiEvent
import com.pocketdimen.lyricpocket.feature.lyric.LyricViewModel
import com.pocketdimen.lyricpocket.feature.home.HomeScreen

@Composable
fun LycordNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreen(
                onAddNewLyricClick = {
                    navController.navigate(AddEditLyricScreenRoute())
                },
                onLyricClick = {
                    navController.navigate(
                        AddEditLyricScreenRoute(
                        id = it.id,
                        title = it.title,
                        content = it.content
                        )
                    )
                }
            )
        }

        composable<AddEditLyricScreenRoute> {
            val viewModel: LyricViewModel = viewModel(factory = LycordViewModelProvider.Factory)
            val arguments = it.toRoute<AddEditLyricScreenRoute>()
            if (arguments.id != null) {
                viewModel.onEvent(LyricUiEvent.OnPopulate(arguments.id, arguments.title, arguments.content))
            }
            AddEditLyricScreen(
                viewModel = viewModel,
                onMenuClick = {

                }
            )
        }
    }
}