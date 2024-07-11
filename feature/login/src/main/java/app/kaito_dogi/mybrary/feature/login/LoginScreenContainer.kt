package app.kaito_dogi.mybrary.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun LoginScreenContainer(
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

  LoginScreen(
    uiState = uiState,
    onMailChange = viewModel::onEmailChange,
    onPasswordChange = viewModel::onPasswordChange,
    onVisibilityClick = viewModel::onVisibilityChange,
    onMailLoginClick = viewModel::onMailLoginClick,
    onGoogleLoginClick = viewModel::onGoogleLoginClick,
    onSignUpClick = onSignUpClick,
  )
}
