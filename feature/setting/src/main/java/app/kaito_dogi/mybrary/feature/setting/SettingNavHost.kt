package app.kaito_dogi.mybrary.feature.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
internal fun SettingNavHost(
  startDestination: SettingRoute,
  builder: NavGraphBuilder.(NavHostController) -> Unit,
  modifier: Modifier = Modifier,
) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = startDestination,
    builder = { builder(navController) },
    modifier = modifier,
  )
}
