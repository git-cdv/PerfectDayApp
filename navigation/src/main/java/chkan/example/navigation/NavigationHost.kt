package chkan.example.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import chkan.example.navigation.internal.NavigationEvent
import chkan.example.navigation.viewmodel.ScreenViewModelStoreOwner
import chkan.example.navigation.viewmodel.ScreenViewModelStoreProvider
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
    //для создания отдельных ВМ для каждого экрана
    val viewModelStoreProvider = viewModel<ScreenViewModelStoreProvider>()
    val viewModelStoreOwner = remember(internalState.currentUuid) {
        ScreenViewModelStoreOwner(viewModelStoreProvider,internalState.currentUuid)
    }

    //for ability save screens in stack with data
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = internalState.currentUuid) {
        Box(modifier = modifier){
            CompositionLocalProvider(
                LocalRouter provides router,
                LocalViewModelStoreOwner provides viewModelStoreOwner
            ) {
                routeMapper.invoke(navigationState.currentRoute)
            }
        }
    }
    //remove route after pop() in stack from saveableStateHolder
    LaunchedEffect(navigation) {
        navigation.internalNavigationState.listen()
            .filterIsInstance<NavigationEvent.Removed>()
            .collect{ event ->
                saveableStateHolder.removeState(event.route.uuid)
                viewModelStoreProvider.removeStore(event.route.uuid)
            }
    }
}