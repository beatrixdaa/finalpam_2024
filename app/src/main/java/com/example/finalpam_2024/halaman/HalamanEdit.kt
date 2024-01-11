package com.example.finalpam_2024.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam_2024.R
import com.example.finalpam_2024.model.EditViewModel
import com.example.finalpam_2024.model.PenyediaViewModel
import com.example.finalpam_2024.navigasi.DestinasiNavigasi
import com.example.finalpam_2024.navigasi.FilmTopAppBar
import kotlinx.coroutines.launch

object ItemEditDestination: DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_Film
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack : () -> Unit,
    onNavigateUp : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel : EditViewModel = viewModel (factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            FilmTopAppBar(
                title = stringResource
                    (ItemEditDestination.titleRes) ,
                canNavigateBack =true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryFilmBody(
            uiStateFilm = viewModel.FilmUiState,
            onFilmValueChange =viewModel::updateUiState,
            onSaveClick = {coroutineScope.launch { viewModel.updateFilm()
                navigateBack()
            }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

