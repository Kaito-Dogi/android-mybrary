package app.kaito_dogi.mybrary.feature.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.component.Gap
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.R

@Composable
internal fun MailSection(
  mail: String,
  onMailChange: (String) -> Unit,
  password: String,
  onPasswordChange: (String) -> Unit,
  onMailLoginClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val (isPasswordVisible, setIsPasswordVisible) = remember { mutableStateOf(false) }

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(MybraryTheme.space.md),
  ) {
    TextField(
      value = mail,
      onValueChange = onMailChange,
      modifier = Modifier.fillMaxWidth(),
      placeholder = {
        Text(text = "メールアドレスを入力…")
      },
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.icon_mail),
          contentDescription = "メールアドレス",
        )
      },
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next,
      ),
    )
    TextField(
      value = password,
      onValueChange = onPasswordChange,
      modifier = Modifier.fillMaxWidth(),
      placeholder = {
        Text(text = "パスワードを入力…")
      },
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.icon_key),
          contentDescription = "パスワード",
        )
      },
      trailingIcon = {
        IconButton(onClick = { setIsPasswordVisible(!isPasswordVisible) }) {
          Icon(
            painter = painterResource(id = if (isPasswordVisible) R.drawable.icon_visibility else R.drawable.icon_visibility_off),
            contentDescription = if (isPasswordVisible) "パスワードを非表示にする" else "パスワードを表示する",
          )
        }
      },
      visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Send,
      ),
      keyboardActions = KeyboardActions(
        onSend = { onMailLoginClick() },
      ),
    )
    Button(
      onClick = onMailLoginClick,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Icon(
        painter = painterResource(id = R.drawable.icon_mail),
        contentDescription = "メールアドレスでログイン",
      )
      Gap(width = MybraryTheme.space.xs)
      Text(text = "メールアドレスでログイン")
    }
  }
}

@Preview
@Composable
private fun MailSectionPreview() {
  MybraryTheme {
    MailSection(
      mail = "",
      onMailChange = {},
      password = "",
      onPasswordChange = {},
      onMailLoginClick = {},
    )
  }
}
