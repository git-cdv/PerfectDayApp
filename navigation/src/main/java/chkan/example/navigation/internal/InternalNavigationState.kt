package chkan.example.navigation.internal

import chkan.example.navigation.Route
import kotlinx.coroutines.flow.Flow

internal sealed class NavigationEvent{
    data class Removed(val route: RouteItem) : NavigationEvent()
}
internal interface InternalNavigationState {
    val currentUuid: String
    fun listen(): Flow<NavigationEvent>
}