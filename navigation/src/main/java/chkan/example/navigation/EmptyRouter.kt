package chkan.example.navigation

import chkan.example.navigation.Route
import chkan.example.navigation.Router

object EmptyRouter : Router {
    override fun launch(route: Route) = Unit
    override fun pop() = Unit
    override fun restart(route: Route) = Unit
}