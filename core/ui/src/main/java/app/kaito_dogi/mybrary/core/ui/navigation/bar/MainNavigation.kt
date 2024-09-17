package app.kaito_dogi.mybrary.core.ui.navigation.bar

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import app.kaito_dogi.mybrary.core.ui.navigation.route.MybraryRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MainRoute

fun NavGraphBuilder.mainNavGraph(
  startDestination: MainRoute.MyBook,
  builder: NavGraphBuilder.() -> Unit,
) = navigation<MybraryRoute.Main>(
  startDestination = startDestination,
  builder = builder,
)
