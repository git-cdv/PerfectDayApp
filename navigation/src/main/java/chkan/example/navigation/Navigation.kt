package chkan.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import chkan.example.navigation.internal.InternalNavigationState
import chkan.example.navigation.internal.RouteItem
import chkan.example.navigation.internal.ScreenStack

@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState : InternalNavigationState,
)

@Composable
fun rememberNavigation (initialRoute: Route): Navigation{
    //for ability save and restore stack
    val stack = rememberSaveable(initialRoute) {
        ScreenStack(mutableStateListOf(RouteItem(initialRoute)))
    }
    return remember(initialRoute) {
        Navigation(router = stack, navigationState = stack, internalNavigationState = stack)
    }
}