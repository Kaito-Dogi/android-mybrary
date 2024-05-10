package app.kaito_dogi.mybrary.core.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.R
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

private const val bookAspectRatio = 210f / 297f

@Composable
fun BookImage(
  imageUrl: String,
  title: String,
  modifier: Modifier = Modifier,
  onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
  onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
  onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
  alignment: Alignment = Alignment.Center,
  alpha: Float = DefaultAlpha,
  colorFilter: ColorFilter? = null,
  filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
) {
  AsyncImage(
    model = imageUrl,
    contentDescription = "${title}の表紙",
    modifier = modifier.aspectRatio(bookAspectRatio),
    placeholder = painterResource(id = R.drawable.ui_book_image_placeholder),
    error = painterResource(id = R.drawable.ui_book_image_placeholder),
    onLoading = onLoading,
    onSuccess = onSuccess,
    onError = onError,
    alignment = alignment,
    contentScale = ContentScale.Crop,
    alpha = alpha,
    colorFilter = colorFilter,
    filterQuality = filterQuality,
  )
}

@Preview
@Composable
private fun BookImagePreview() {
  MybraryTheme {
    BookImage(
      imageUrl = "imageUrl",
      title = "title",
    )
  }
}
