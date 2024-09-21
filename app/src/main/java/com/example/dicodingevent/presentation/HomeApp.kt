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

    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                   when(selectedItemIndex){
                       0 -> Text("All Event")
                       1 -> Text("Upcoming Event")
                       2 -> Text("Finished Event")
                   }
                }
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
                                0 -> navController.navigate(NavScreen.Home.route)
                                1 -> navController.navigate(NavScreen.Upcoming.route)
                                2 -> navController.navigate(NavScreen.Finished.route)
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