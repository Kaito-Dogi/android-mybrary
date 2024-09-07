package app.kaito_dogi.mybrary.feature.sendotp.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.R

@Composable
internal fun LoginSection(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val annotatedString = buildAnnotatedString {
    withStyle(
      style = SpanStyle(
        color = MybraryTheme.colorScheme.onBackground,
      ),
    ) {
      append(context.getString(R.string.send_otp_text_already_have_an_account_login))
    }

    withStyle(
      style = SpanStyle(
        textDecoration = TextDecoration.Underline,
      ),
    ) {
      append(context.getString(R.string.send_otp_text_here))
    }
  }

  TextButton(
    onClick = onClick,
    modifier = modifier,
  ) {
    Text(text = annotatedString)
  }
}

@Preview
@Composable
private fun LoginSectionPreview() {
  MybraryTheme {
    LoginSection(onClick = {})
  }
}
