package com.example.dicodingevent.navigation

sealed class NavScreen(val route: String) {
    data object Home : NavScreen("home")
    data object Upcoming : NavScreen("upcoming")
    data object Finished : NavScreen("finished")
    data object HomeApp : NavScreen("homeApp")
    data object EventDetail : NavScreen("eventDetail/{id}") {
        fun createRoute(id: Int) = "eventDetail/$id"
    }
}
