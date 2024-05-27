package chkan.example.perfectday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import chkan.example.navigation.NavigationHost
import chkan.example.navigation.EmptyRouter
import chkan.example.navigation.rememberNavigation
import chkan.example.perfectday.core.startExitAnimation
import chkan.example.perfectday.routes.AppRoute
import chkan.example.perfectday.ui.screens.AddTaskScreen
import chkan.example.perfectday.ui.screens.MainScreen
import chkan.example.perfectday.ui.theme.PerfectDayTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !viewModel.isReady.value
            }
            setOnExitAnimationListener{ screen ->
                screen.startExitAnimation()
            }
        }
        setContent {
            PerfectDayTheme {
                val navigation = rememberNavigation(initialRoute = AppRoute.MainScreen)
                val (router, _) = navigation
                NavigationHost(navigation) { currentRoute ->
                    when(currentRoute){
                        AppRoute.MainScreen -> MainScreen(router)
                        AppRoute.AddTaskScreen -> AddTaskScreen()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PerfectDayTheme {
        MainScreen(EmptyRouter)
    }
}