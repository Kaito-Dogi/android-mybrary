package app.kaito_dogi.mybrary.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme

@Composable
fun TextField(
  value: String,
  onValueChange: (String) -> Unit,
  @StringRes placeholderResId: Int,
  modifier: Modifier = Modifier,
  @DrawableRes leadingIconResId: Int? = null,
  @StringRes leadingIconAltResId: Int? = null,
  isEnabled: Boolean = true,
  isError: Boolean = false,
  keyboardType: KeyboardType = KeyboardType.Text,
  imeAction: ImeAction = ImeAction.Default,
  onSend: (() -> Unit)? = null,
  singleLine: Boolean = false,
  minLines: Int = 1,
  shape: Shape = MybraryTheme.shapes.small,
) = androidx.compose.material3.OutlinedTextField(
  value = value,
  onValueChange = onValueChange,
  modifier = modifier,
  enabled = isEnabled,
  placeholder = {
    Text(text = stringResource(id = placeholderResId))
  },
  leadingIcon = leadingIconResId?.let {
    {
      Icon(
        painter = painterResource(id = it),
        contentDescription = leadingIconAltResId?.let { stringResource(id = it) },
        modifier = Modifier.size(ButtonDefaults.IconSize),
      )
    }
  },
  isError = isError,
  keyboardOptions = KeyboardOptions(
    keyboardType = keyboardType,
    imeAction = imeAction,
  ),
  keyboardActions = KeyboardActions(
    onSend = onSend?.let { { onSend() } },
  ),
  singleLine = singleLine,
  minLines = minLines,
  shape = shape,
  colors = OutlinedTextFieldDefaults.colors().copy(
    focusedContainerColor = MybraryTheme.colorScheme.surface,
    unfocusedContainerColor = MybraryTheme.colorScheme.surface,
    disabledContainerColor = MybraryTheme.colorScheme.surface,
    errorContainerColor = MybraryTheme.colorScheme.surface,
  ),
)

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview(
  @PreviewParameter(TextFieldPreviewParameterProvider::class) parameter: TextFieldPreviewParameter,
) {
  MybraryTheme {
    TextField(
      value = "",
      onValueChange = {},
      placeholderResId = android.R.string.unknownName,
      isError = parameter.isError,
    )
  }
}

private class TextFieldPreviewParameterProvider :
  PreviewParameterProvider<TextFieldPreviewParameter> {
  override val values: Sequence<TextFieldPreviewParameter>
    get() = sequenceOf(
      TextFieldPreviewParameter(
        isEnabled = true,
        isError = false,
      ),
      TextFieldPreviewParameter(
        isEnabled = true,
        isError = true,
      ),
      TextFieldPreviewParameter(
        isEnabled = false,
        isError = false,
      ),
    )
}

private data class TextFieldPreviewParameter(
  val isEnabled: Boolean,
  val isError: Boolean,
)
