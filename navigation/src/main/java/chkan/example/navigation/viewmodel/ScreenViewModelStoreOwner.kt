package chkan.example.navigation.viewmodel

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

internal class ScreenViewModelStoreOwner(
    provide: ScreenViewModelStoreProvider,
    uuid: String
) : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore =
        provide.getStore(uuid)
}