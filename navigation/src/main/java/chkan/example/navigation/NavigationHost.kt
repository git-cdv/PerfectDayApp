package chkan.example.navigation

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import chkan.example.navigation.internal.NavigationEvent
import chkan.example.navigation.viewmodel.ScreenViewModelStoreOwner
import chkan.example.navigation.viewmodel.ScreenViewModelStoreProvider
import kotlinx.coroutines.flow.filterIsInstance
import kotlin.math.roundToInt

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

    val density = LocalDensity.current
    val statusBarHeight = WindowInsets.statusBars.getTop(density).pxToDp(density.density)
    val navigatorBarHeight = WindowInsets.navigationBars.getBottom(density).pxToDp(density.density)

    //for ability save screens in stack with data
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = internalState.currentUuid) {
        Box(modifier =
        modifier
            .padding(top = statusBarHeight, bottom = navigatorBarHeight)
        ){
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

fun Int.pxToDp(density: Float): Dp{
    return (this / density).dp
}