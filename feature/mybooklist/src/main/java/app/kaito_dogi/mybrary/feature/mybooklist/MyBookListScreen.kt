package app.kaito_dogi.mybrary.feature.mybooklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.domain.model.MyBook
import app.kaito_dogi.mybrary.feature.mybooklist.component.MyBookCell
import app.kaito_dogi.mybrary.feature.mybooklist.component.MyBookCellSkeleton

@Composable
internal fun MyBookListScreen(
  uiState: MyBookListUiState,
  onAdditionClick: () -> Unit,
  onMyBookClick: (MyBook) -> Unit,
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    floatingActionButton = {
      FloatingActionButton(
        onClick = onAdditionClick,
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "書籍検索画面に遷移",
        )
      }
    },
  ) { innerPadding ->
    LazyVerticalGrid(
      columns = GridCells.Fixed(uiState.numberOfColumns),
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(
        start = MybraryTheme.space.md,
        top = innerPadding.calculateTopPadding(),
        end = MybraryTheme.space.md,
        bottom = innerPadding.calculateBottomPadding(),
      ),
      verticalArrangement = Arrangement.spacedBy(MybraryTheme.space.sm),
      horizontalArrangement = Arrangement.spacedBy(MybraryTheme.space.sm),
    ) {
      uiState.myBookList?.let {
        items(
          items = uiState.myBookList,
          key = { it.id.value },
        ) {
          MyBookCell(
            myBook = it,
            onClick = onMyBookClick,
          )
        }
      } ?: items(
        count = 4,
        key = { "MyBookCellSkeleton$it" },
      ) {
        MyBookCellSkeleton()
      }
    }
  }
}

// TODO: Loading, Success 時の Preview を表示する
@Preview
@Composable
private fun MyBookListScreenPreview() {
  MybraryTheme {
    MyBookListScreen(
      uiState = MyBookListUiState.InitialValue,
      onAdditionClick = {},
      onMyBookClick = {},
    )
  }
}
