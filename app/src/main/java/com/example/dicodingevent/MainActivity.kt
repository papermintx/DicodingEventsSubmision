package com.example.dicodingevent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dicodingevent.navigation.NavScreen
import com.example.dicodingevent.ui.presentation.HomeApp
import com.example.dicodingevent.ui.presentation.detail.event.DetailEventScreen
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
