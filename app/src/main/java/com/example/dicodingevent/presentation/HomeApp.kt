package com.example.dicodingevent.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dicodingevent.BottomNavigationItem
import com.example.dicodingevent.navigation.NavScreen
import com.example.dicodingevent.navigation.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp(modifier: Modifier = Modifier, eventtoDetailClick: (Int) -> Unit) {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home
        ),
        BottomNavigationItem(
            title = "Upcoming",
            selectedIcon = Icons.Filled.Upcoming,
            unselectedIcon = Icons.Filled.Upcoming
        ),
        BottomNavigationItem(
            title = "Finished",
            selectedIcon = Icons.Filled.Update,
            unselectedIcon = Icons.Filled.Update
        )
    )

    var selectedItemIndex by remember { mutableStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dicoding Event") }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(NavScreen.Home.route) {
                                    popUpTo(NavScreen.Home.route) { inclusive = false }
                                }
                                1 -> navController.navigate(NavScreen.Upcoming.route) {
                                    popUpTo(NavScreen.Upcoming.route) { inclusive = false }
                                }
                                2 -> navController.navigate(NavScreen.Finished.route) {
                                    popUpTo(NavScreen.Finished.route) { inclusive = false }
                                }
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
        Navigation(navController = navController, paddingValues = paddingValues, eventtoDetailClick = eventtoDetailClick)
    }

}