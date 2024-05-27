package chkan.example.navigation

import androidx.compose.runtime.Stable

@Stable
interface NavigationState {
    val currentRoute: Route
    val isRoot: Boolean
}