package chkan.example.navigation.internal

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.os.ParcelCompat
import chkan.example.navigation.NavigationState
import chkan.example.navigation.Route
import chkan.example.navigation.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class ScreenStack(
    private val routes: SnapshotStateList<Route>
): NavigationState, Router, InternalNavigationState, Parcelable {

    override val isRoot: Boolean
        get() = routes.size == 1
    override val currentRoute: Route
        get() = routes.last()

    private val eventsFlow = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = Int.MAX_VALUE)

    override fun launch(route: Route) {
        routes.add(route)
    }

    override fun pop() {
        val removedRoute = routes.removeLastOrNull()
        if (removedRoute != null) eventsFlow.tryEmit(NavigationEvent.Removed(removedRoute))
    }

    override fun restart(route: Route) {
        routes.apply {
            routes.forEach {
                eventsFlow.tryEmit(NavigationEvent.Removed(it))
            }
            clear()
            add(route)
        }
    }

    override fun listen(): Flow<NavigationEvent> {
        return eventsFlow
    }

    //for ability save and restore stack
    constructor(parcel: Parcel) : this(
        SnapshotStateList<Route>().also { newList ->
            ParcelCompat.readList(
                parcel,
                newList,
                Route::class.java.classLoader,
                Route::class.java,
            )
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(routes)
    }

    override fun describeContents(): Int { return 0 }

    companion object CREATOR : Parcelable.Creator<ScreenStack> {
        override fun createFromParcel(parcel: Parcel): ScreenStack {
            return ScreenStack(parcel)
        }

        override fun newArray(size: Int): Array<ScreenStack?> {
            return arrayOfNulls(size)
        }
    }
}