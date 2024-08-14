package app.kaito_dogi.mybrary.feature.auth.route.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun LoginScreenContainer(
  onSendOtpComplete: (email: String) -> Unit,
  onLoginComplete: () -> Unit,
  onSignUpClick: () -> Unit,
  viewModel: LoginViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  if (uiState.isLoggedIn) {
    LaunchedEffect(Unit) {
      onLoginComplete()
    }
  }

  if (uiState.isOtpSent) {
    LaunchedEffect(Unit) {
      onSendOtpComplete(uiState.email)
    }
  }

  LoginScreen(
    uiState = uiState,
    onEmailChange = viewModel::onEmailChange,
    onSendOtpClick = viewModel::onSendOtpClick,
    onGoogleLoginClick = viewModel::onGoogleLoginClick,
    onSignUpClick = onSignUpClick,
  )
}
