package app.kaito_dogi.mybrary.feature.auth.route.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.component.Gap
import app.kaito_dogi.mybrary.core.designsystem.component.button.SecondaryButton
import app.kaito_dogi.mybrary.core.designsystem.ext.plus
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.R
import app.kaito_dogi.mybrary.feature.auth.component.DividerSection
import app.kaito_dogi.mybrary.feature.auth.component.EmailSection
import app.kaito_dogi.mybrary.feature.auth.component.LogoSection
import app.kaito_dogi.mybrary.feature.auth.route.login.component.SignUpSection

@Composable
internal fun LoginScreen(
  uiState: LoginUiState,
  onEmailChange: (String) -> Unit,
  onSendOtpClick: () -> Unit,
  onGoogleLoginClick: () -> Unit,
  onSignUpClick: () -> Unit,
) {
  Scaffold(
    modifier = Modifier.imePadding(),
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(
          innerPadding.plus(
            start = MybraryTheme.space.xl,
            top = MybraryTheme.space.xxxl,
            end = MybraryTheme.space.xl,
            bottom = MybraryTheme.space.xl,
          ),
        ),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      LogoSection()

      Gap(height = MybraryTheme.space.xxl)
      Gap(height = MybraryTheme.space.xl)

      EmailSection(
        email = uiState.email,
        onEmailChange = onEmailChange,
        onSendOtpClick = onSendOtpClick,
      )

      Gap(height = MybraryTheme.space.xl)

      DividerSection()

      Gap(height = MybraryTheme.space.xl)

      SecondaryButton(
        textResId = R.string.auth_text_login_with_google,
        onClick = onGoogleLoginClick,
        modifier = Modifier.fillMaxWidth(),
        iconResId = R.drawable.icon_google,
        altResId = R.string.auth_alt_login_with_google,
        iconTint = Color.Unspecified,
      )

      Spacer(modifier = Modifier.weight(1f))

      SignUpSection(onClick = onSignUpClick)
    }
  }
}

@Preview
@Composable
private fun LoginScreenPreview() {
  MybraryTheme {
    LoginScreen(
      uiState = LoginUiState.InitialValue,
      onEmailChange = {},
      onSendOtpClick = {},
      onGoogleLoginClick = {},
      onSignUpClick = {},
    )
  }
}
