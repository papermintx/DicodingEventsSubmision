package com.example.dicodingevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dicodingevent.navigation.NavScreen
import com.example.dicodingevent.navigation.Navigation
import com.example.dicodingevent.presentation.HomeApp
import com.example.dicodingevent.presentation.detailscreen.DetailEventScreen
import com.example.dicodingevent.ui.theme.DicodingEventTheme
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DicodingEventTheme {
                val navController2 = rememberNavController()
                NavHost(
                    navController = navController2,
                    startDestination = NavScreen.HomeApp.route,
                ){
                    composable(
                        route = NavScreen.HomeApp.route
                    ){
                        HomeApp(
                            eventtoDetailClick = { id ->
                                navController2.navigate(NavScreen.EventDetail.createRoute(id))
                            }
                        )
                    }
                    composable(
                        route = NavScreen.EventDetail.route,
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")
                        if (id != null) {
                            DetailEventScreen(
                                id = id,
                                onBack = {
                                    navController2.popBackStack()
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}
