package app.kaito_dogi.mybrary.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import app.kaito_dogi.mybrary.core.ui.navigation.route.AppRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MainRoute

fun NavGraphBuilder.mainNavGraph(
  startDestination: MainRoute.MyBook,
  builder: NavGraphBuilder.() -> Unit,
) = navigation<AppRoute.Main>(
  startDestination = startDestination,
  builder = builder,
)

fun NavHostController.navigateToMainNavGraph() = this.navigate(AppRoute.Main)
