package app.kaito_dogi.mybrary.feature.mybookdetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.kaito_dogi.mybrary.core.designsystem.component.FullScrimModalBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyBookDetailScreenContainer(
  viewModel: MyBookDetailViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  val coroutineScope = rememberCoroutineScope()
  val bottomSheetState = rememberModalBottomSheetState()
  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(Unit) {
    viewModel.init()
  }

  uiState.shownMessage?.let {
    LaunchedEffect(it) {
      snackbarHostState.showSnackbar(it)
      viewModel.onMessageShow()
    }
  }

  MyBookDetailScreen(
    uiState = uiState,
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    bottomSheet = {
      FullScrimModalBottomSheet(
        onDismissRequest = viewModel::onBottomSheetDismissRequest,
        sheetState = bottomSheetState,
        content = it,
      )
    },
    onArchiveClick = viewModel::onArchiveClick,
    onPublicClick = viewModel::onPublicClick,
    onFavoriteClick = viewModel::onFavoriteClick,
    onAdditionClick = viewModel::onAdditionClick,
    onMemoClick = viewModel::onMemoClick,
    onStartPageChange = viewModel::onStartPageChange,
    onEndPageChange = viewModel::onEndPageChange,
    onContentChange = viewModel::onContentChange,
    onSaveClick = {
      viewModel.onSaveClick(
        onComplete = {
          coroutineScope.launch {
            bottomSheetState.hide()
          }.invokeOnCompletion {
            if (!bottomSheetState.isVisible) {
              viewModel.onBottomSheetDismissRequest()
            }
          }
        },
      )
    },
  )
}
