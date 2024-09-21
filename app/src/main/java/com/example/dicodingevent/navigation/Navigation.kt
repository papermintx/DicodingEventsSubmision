package com.example.dicodingevent.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dicodingevent.presentation.ListEvent
import com.example.dicodingevent.presentation.detailscreen.DetailEventScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier, paddingValues: PaddingValues,eventtoDetailClick: (Int)-> Unit) { // Change to NavHostController
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route,
    ) {
        composable(NavScreen.Home.route) {
            ListEvent(
                active = -1,
                onItemClick = eventtoDetailClick,
                paddingValues = paddingValues,
            )
        }
        composable(NavScreen.Upcoming.route) {
            ListEvent(
                active = 1,
                onItemClick = eventtoDetailClick,
                paddingValues = paddingValues,
            )
        }
        composable(NavScreen.Finished.route) {
            ListEvent(
                active = 0,
                onItemClick = eventtoDetailClick,
                paddingValues = paddingValues,

            )
        }

    }
}
