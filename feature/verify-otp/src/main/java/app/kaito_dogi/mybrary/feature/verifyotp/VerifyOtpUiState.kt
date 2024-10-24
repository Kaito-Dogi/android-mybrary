package app.kaito_dogi.mybrary.feature.verifyotp

import androidx.compose.runtime.Immutable
import app.kaito_dogi.mybrary.core.common.model.CaptchaToken
import app.kaito_dogi.mybrary.core.ui.navigation.route.AuthRoute

// FIXME: UiState と ViewModelState を分けてみる
@Immutable
internal data class VerifyOtpUiState(
  val email: String,
  val captchaToken: CaptchaToken,
  val source: AuthRoute.VerifyOtp.Source,
  val otp: String,
  val isOtpVerifying: Boolean,
  val isOtpResending: Boolean,
) {
  companion object {
    fun createInitialValue(
      email: String,
      captchaToken: CaptchaToken,
      source: AuthRoute.VerifyOtp.Source,
    ) = VerifyOtpUiState(
      email = email,
      captchaToken = captchaToken,
      source = source,
      otp = "",
      isOtpVerifying = false,
      isOtpResending = false,
    )
  }

  val maskedEmail = email.let {
    val atIndex = email.indexOf(string = "@")

    // "@" が1文字目の場合はマスクしない
    if (atIndex <= 1) return@let email

    val userName = email.substring(0, atIndex)
    val domainName = email.substring(atIndex)

    val maskedUserName = when (userName.length) {
      1 -> userName
      2 -> "${userName[0]}*"
      else -> "${userName[0]}${"*".repeat(userName.length - 2)}${userName[userName.length - 1]}"
    }

    return@let maskedUserName + domainName
  }
}
