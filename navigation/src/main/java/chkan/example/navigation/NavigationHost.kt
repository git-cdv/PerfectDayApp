package chkan.example.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import chkan.example.navigation.internal.NavigationEvent
import kotlinx.coroutines.flow.filterIsInstance

//для обращения к роутеру из любого экрана
val LocalRouter = staticCompositionLocalOf<Router> { EmptyRouter }
@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
    routeMapper: @Composable (Route) -> Unit,
){
    val (router, navigationState,internalState) = navigation
    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    //for ability save screens in stack with data
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = internalState.currentUuid) {
        Box(modifier = modifier){
            CompositionLocalProvider(LocalRouter provides router) {
                routeMapper.invoke(navigationState.currentRoute)
            }
        }
    }
    //remove route after pop() in stack from saveableStateHolder
    LaunchedEffect(navigation) {
        navigation.internalNavigationState.listen()
            .filterIsInstance<NavigationEvent.Removed>()
            .collect{ event ->
                saveableStateHolder.removeState(event.route)
            }
    }
}