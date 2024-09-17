package app.kaito_dogi.mybrary

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.exception.ExceptionConsumer
import app.kaito_dogi.mybrary.core.ui.exception.ExceptionConsumerEntryPoint
import app.kaito_dogi.mybrary.core.ui.navigation.AppNavHost
import app.kaito_dogi.mybrary.core.ui.navigation.bar.mainNavGraph
import app.kaito_dogi.mybrary.core.ui.navigation.route.AppRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MainRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MyBookRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.SettingRoute
import app.kaito_dogi.mybrary.feature.mybook.myBookDestination
import app.kaito_dogi.mybrary.feature.mybook.destination.mybooklist.myBookListScreen
import app.kaito_dogi.mybrary.feature.setting.destination.settinglist.settingListScreen
import app.kaito_dogi.mybrary.feature.setting.settingDestination
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    // FIXME: Deep Links の実装が完了次第削除する
    println("あああ: data: ${intent.data}")
    println("あああ: action: ${intent.action}")

    setContent {
      MybraryTheme {
        // FIXME: 共通化する
        val context = LocalContext.current
        val exceptionConsumer: ExceptionConsumer = remember(context) {
          EntryPointAccessors.fromApplication<ExceptionConsumerEntryPoint>(context.applicationContext)
            .exceptionConsumer()
        }
        val exception = exceptionConsumer.exception.collectAsStateWithLifecycle()
        LaunchedEffect(exception) {
          // FIXME: 共通のエラー処理を実行
          exceptionConsumer.consumeException()
        }

        // FIXME: ログイン状態に応じて startDestination を変更する
        AppNavHost(startDestination = AppRoute.Main) { navController ->
          mainNavGraph(startDestination = MainRoute.MyBook) {
            myBookDestination(startDestination = MyBookRoute.MyBookList) {
              myBookListScreen(
                onAdditionClick = {
                  // navController.navigate(MybraryRoute.SearchBook)
                },
                onMyBookClick = { myBook ->
                  // val route = MybraryRoute.MyBookDetail(myBook = myBook)
                  // navController.navigate(route)
                },
              )
            }

            settingDestination(startDestination = SettingRoute.SettingList) {
              settingListScreen()
            }
          }
        }
      }
    }
  }
}
