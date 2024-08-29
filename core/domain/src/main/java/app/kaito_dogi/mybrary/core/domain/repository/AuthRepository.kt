package app.kaito_dogi.mybrary.core.domain.repository

// FIXME: LoginRepository, SignUpRepository, OtpRepository に分解して削除する
interface AuthRepository {
  suspend fun sendOtp(email: String)

  suspend fun verifyOtp(
    email: String,
    otp: String,
  )

  suspend fun googleLogin()

  // FIXME: 適切な定義箇所に移動する
  suspend fun hasSession(): Boolean
}
