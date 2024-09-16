package app.kaito_dogi.mybrary.feature.setting.route.settinglist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.kaito_dogi.mybrary.feature.setting.SettingRoute

fun NavGraphBuilder.settingListScreen(
) = composable<SettingRoute.SettingList> {
  SettingListScreenContainer()
}
