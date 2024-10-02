package app.kaito_dogi.mybrary

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.browser.InternalBrowserLauncher
import app.kaito_dogi.mybrary.core.ui.exception.ExceptionConsumer
import app.kaito_dogi.mybrary.core.ui.exception.ExceptionConsumerEntryPoint
import app.kaito_dogi.mybrary.core.ui.navigation.MybraryNavHost
import app.kaito_dogi.mybrary.core.ui.navigation.bar.mainNavGraph
import app.kaito_dogi.mybrary.core.ui.navigation.route.AuthRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MainRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MyBookRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.MybraryRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.SearchBookRoute
import app.kaito_dogi.mybrary.core.ui.navigation.route.SettingRoute
import app.kaito_dogi.mybrary.feature.auth.authNavGraph
import app.kaito_dogi.mybrary.feature.auth.destination.sendotp.navigateToSendOtpScreen
import app.kaito_dogi.mybrary.feature.auth.destination.sendotp.sendOtpScreen
import app.kaito_dogi.mybrary.feature.auth.destination.verifyotp.navigateToVerifyOtpScreen
import app.kaito_dogi.mybrary.feature.auth.destination.verifyotp.verifyOtpScreen
import app.kaito_dogi.mybrary.feature.mybook.destination.mybookdetail.myBookDetailScreen
import app.kaito_dogi.mybrary.feature.mybook.destination.mybookdetail.navigateToMyBookDetailScreen
import app.kaito_dogi.mybrary.feature.mybook.destination.mybooklist.myBookListScreen
import app.kaito_dogi.mybrary.feature.mybook.destination.mybooklist.navigateToMyBookListScreen
import app.kaito_dogi.mybrary.feature.mybook.myBookDestination
import app.kaito_dogi.mybrary.feature.searchbook.destination.searchbook.navigateToSearchBookScreen
import app.kaito_dogi.mybrary.feature.searchbook.destination.searchbook.searchBookScreen
import app.kaito_dogi.mybrary.feature.searchbook.searchBookNavGraph
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
        MybraryNavHost(startDestination = MybraryRoute.Auth) { navController: NavHostController, internalBrowserLauncher: InternalBrowserLauncher ->
          authNavGraph(startDestination = AuthRoute.SendOtp) {
            sendOtpScreen(
              onSendOtpComplete = { email, page ->
                navController.navigateToVerifyOtpScreen(
                  email = email,
                  page = page,
                )
              },
              onLoginComplete = navController::navigateToMyBookListScreen,
              onSignUpComplete = navController::navigateToMyBookListScreen,
            )

            verifyOtpScreen(
              onVerifyOtpComplete = navController::navigateToMyBookListScreen,
              onNavigationIconClick = navController::popBackStack,
            )
          }

          mainNavGraph(startDestination = MainRoute.MyBook) {
            myBookDestination(startDestination = MyBookRoute.MyBookList) {
              myBookListScreen(
                onAdditionClick = navController::navigateToSearchBookScreen,
                onMyBookClick = { myBook ->
                  navController.navigateToMyBookDetailScreen(myBook = myBook)
                },
              )

              myBookDetailScreen(
                onNavigationIconClick = navController::popBackStack,
              )

              searchBookNavGraph(startDestination = SearchBookRoute.SearchBook) {
                searchBookScreen()
              }
            }

            settingDestination(startDestination = SettingRoute.SettingList) {
              settingListScreen(
                onLogoutComplete = navController::navigateToSendOtpScreen,
                onTermsOfUseClick = internalBrowserLauncher::launch,
                onPrivacyPolicyClick = internalBrowserLauncher::launch,
                onLicenceClick = {},
                onRakutenDevelopersClick = internalBrowserLauncher::launch,
                onDeleteAccountClick = internalBrowserLauncher::launch,
              )
            }
          }
        }
      }
    }
  }
}
