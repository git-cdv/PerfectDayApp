package chkan.example.perfectday.routes

import chkan.example.navigation.Route

sealed class AppRoute : Route {
    data object MainScreen: AppRoute()
    data object AddTaskScreen: AppRoute()
}