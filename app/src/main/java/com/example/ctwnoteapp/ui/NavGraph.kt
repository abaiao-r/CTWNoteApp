// NavGraph.kt
package com.example.ctwnoteapp.ui


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ctwnoteapp.viewmodel.NoteViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: NoteViewModel) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "noteList",
        modifier = Modifier,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
        }
    ) {
        composable("noteList") {
            NoteListScreen(navController, viewModel)
        }
        composable("noteInput") {
            NoteInputScreen(navController, viewModel, context)
        }
        composable("trash") {
            TrashScreen(navController, viewModel)
        }
    }
}

//@Composable
//fun NavGraph(navController: NavHostController, viewModel: NoteViewModel) {
//    val context = LocalContext.current
//
//    NavHost(navController = navController, startDestination = "noteList") {
//        composable("noteList") {
//            NoteListScreen(navController, viewModel)
//        }
//        composable("noteInput") {
//            NoteInputScreen(navController, viewModel, context)
//        }
//    }
//}
