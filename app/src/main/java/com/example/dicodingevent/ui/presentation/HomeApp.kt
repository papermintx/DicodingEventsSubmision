package com.example.dicodingevent.ui.presentation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.dicodingevent.BottomNavigationItem
import com.example.dicodingevent.navigation.NavScreen
import com.example.dicodingevent.navigation.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp(eventtoDetailClick: (Int) -> Unit) {
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

    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(NavScreen.Home.route)
                                1 -> navController.navigate(NavScreen.Finished.route)
                                2 -> navController.navigate(NavScreen.Favorite.route)
                                3 -> navController.navigate(NavScreen.Search.route)
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
        Navigation(navController = navController, paddingValues = paddingValues, eventDetailClick = eventtoDetailClick)
    }

}