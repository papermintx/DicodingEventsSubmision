package com.example.dicodingevent.navigation
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dicodingevent.ui.presentation.favorite.event.FavoriteEvent
import com.example.dicodingevent.ui.presentation.finished.event.FinishedScreen
import com.example.dicodingevent.ui.presentation.home.HomeScreen
import com.example.dicodingevent.ui.presentation.search.event.SearchScreen
import com.example.dicodingevent.ui.presentation.upcomming.event.UpcomingScreen

@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues, eventDetailClick: (Int)-> Unit, eventToSettingClick: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route,
    ) {
        composable(NavScreen.Home.route) {
            HomeScreen(
                onItemClick = eventDetailClick,
                paddingValues = paddingValues,
                onSettingsClick = eventToSettingClick
            )
        }
        composable(NavScreen.Favorite.route){
            FavoriteEvent(
                paddingValues = paddingValues,
                onEventClick = eventDetailClick
            )
        }
        composable(NavScreen.Upcoming.route){
            UpcomingScreen(
                modifier = Modifier
                    .padding(paddingValues),
                onClick = eventDetailClick
            )
        }
        composable(NavScreen.Finished.route){
            FinishedScreen(
                modifier = Modifier
                    .padding(paddingValues),
                onClick = eventDetailClick
            )
        }
        composable(NavScreen.Search.route){
            SearchScreen(
                modifier = Modifier
                    .padding(paddingValues),
                onClick = eventDetailClick
            )
        }

    }
}
