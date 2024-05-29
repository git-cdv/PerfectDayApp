package chkan.example.perfectday.routes

import chkan.example.navigation.Route
import kotlinx.parcelize.Parcelize

sealed class AppRoute : Route {
    @Parcelize
    data object MainScreen: AppRoute()
    @Parcelize
    data object AddTaskScreen: AppRoute()
}