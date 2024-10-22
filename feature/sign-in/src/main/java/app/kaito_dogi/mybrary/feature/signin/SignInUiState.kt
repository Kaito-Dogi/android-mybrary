package app.kaito_dogi.mybrary.feature.signin

import androidx.compose.runtime.Immutable

@Immutable
internal data class SignInUiState(
  val email: String,
  val isOtpSending: Boolean,
  val isGoogleSigningIn: Boolean,
) {
  companion object {
    val InitialValue = SignInUiState(
      email = "",
      isOtpSending = false,
      isGoogleSigningIn = false,
    )
  }

  val isHCaptchaVisible: Boolean = isOtpSending
}
