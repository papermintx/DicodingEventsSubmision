package com.example.dicodingevent.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dicodingevent.presentation.ListEvent

@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues,eventtoDetailClick: (Int)-> Unit) { // Change to NavHostController
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
