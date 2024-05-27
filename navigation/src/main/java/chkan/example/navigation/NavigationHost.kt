package chkan.example.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

//для обращения к роутеру из любого экрана
val LocalRouter = staticCompositionLocalOf<Router> { EmptyRouter }
@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
    routeMapper: @Composable (Route) -> Unit,
){
    val (router, navigationState) = navigation
    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    Box(modifier = modifier){
        CompositionLocalProvider(LocalRouter provides router) {
            routeMapper.invoke(navigationState.currentRoute)
        }
    }
}