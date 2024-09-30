package com.example.dicodingevent.ui.presentation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.dicodingevent.BottomNavigationItem
import com.example.dicodingevent.navigation.NavScreen
import com.example.dicodingevent.navigation.Navigation

@Composable
fun HomeApp(eventDetailClick: (Int) -> Unit, eventToSettingClick: () -> Unit) {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home
        ),
        BottomNavigationItem(
            title = "Finished",
            selectedIcon = Icons.Filled.History,
            unselectedIcon = Icons.Filled.History
        ),
        BottomNavigationItem(
            title = "Upcoming",
            selectedIcon = Icons.Filled.Upcoming,
            unselectedIcon = Icons.Filled.Upcoming
        ),
        BottomNavigationItem(
            title = "Favorite",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Filled.Favorite
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Filled.Search
        ),
    )

    val navController = rememberNavController()
    var selectedItemIndex by remember { mutableStateOf(0) }

    // Listen to the back stack to update the selected index accordingly
    navController.addOnDestinationChangedListener { _, destination, _ ->
        selectedItemIndex = when (destination.route) {
            NavScreen.Home.route -> 0
            NavScreen.Finished.route -> 1
            NavScreen.Upcoming.route -> 2
            NavScreen.Favorite.route -> 3
            NavScreen.Search.route -> 4
            else -> 0
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            if (selectedItemIndex != index) {
                                selectedItemIndex = index
                                navController.navigate(item.title) // Use title to match the screen
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedItemIndex == index) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                    )
                }
            }
        }
    ) { paddingValues ->
        Navigation(navController = navController, paddingValues = paddingValues, eventDetailClick = eventDetailClick, eventToSettingClick = eventToSettingClick)
    }
}
