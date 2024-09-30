package com.example.dicodingevent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dicodingevent.navigation.NavScreen
import com.example.dicodingevent.ui.presentation.HomeApp
import com.example.dicodingevent.ui.presentation.detail.event.DetailEventScreen
import com.example.dicodingevent.ui.presentation.settings.SettingViewModel
import com.example.dicodingevent.ui.presentation.settings.SettingsScreen
import com.example.dicodingevent.ui.theme.DicodingEventTheme
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SettingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkModeActive by viewModel.isDarkModeActive.collectAsState(
                initial = false
            )

            val isDailyReminderActive by viewModel.isDailyReminderActive.collectAsState(
                initial = false
            )

            DicodingEventTheme(
                darkTheme = isDarkModeActive
            ) {
                val navController2 = rememberNavController()
                NavHost(
                    navController = navController2,
                    startDestination = NavScreen.HomeApp.route,
                ){
                    composable(
                        route = NavScreen.HomeApp.route
                    ){
                        HomeApp(
                            eventDetailClick = { id ->
                                navController2.navigate(NavScreen.EventDetail.createRoute(id))
                            },
                            eventToSettingClick = {
                                navController2.navigate(NavScreen.Setting.route)
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
                    composable(
                        route = NavScreen.Setting.route
                    ){
                        SettingsScreen(isDarkModeActive, isDailyReminderActive)
                    }

                }
            }
        }
    }
}

