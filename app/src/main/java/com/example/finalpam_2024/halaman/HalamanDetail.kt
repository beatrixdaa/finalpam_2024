package com.example.finalpam_2024.halaman

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam_2024.R
import com.example.finalpam_2024.data.Film
import com.example.finalpam_2024.model.DetailsViewModel
import com.example.finalpam_2024.model.ItemDetailUiState
import com.example.finalpam_2024.model.PenyediaViewModel
import com.example.finalpam_2024.model.toFilm
import com.example.finalpam_2024.navigasi.FilmTopAppBar
import kotlinx.coroutines.launch

object DetailsDestination : DestinasiNavigasi {
    override val route = "item_detail"
    override val titleRes = R.string.detail_Film
    const val FilmIdArg = "itemId"
    val routeWithArgs = "$route/{$FilmIdArg}"

}
@Composable
private fun ItemDetailsBody(
    itemDetailsUiState : ItemDetailUiState,
    onDelete : () -> Unit,
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement =  Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))

    ){
        var deleteConfirmationRequired by rememberSaveable{ mutableStateOf(false) }

        ItemDetails(
            Film = itemDetailsUiState.detailFilm.toFilm(),
            modifier =  Modifier.fillMaxWidth()
        )
        OutlinedButton(
            onClick = {deleteConfirmationRequired = true},
            shape= MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()

        )
        {   OutlinedButton(
            onClick = {deleteConfirmationRequired = true},
            shape= MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text (stringResource(id = R.string.delete))
        }
            if (deleteConfirmationRequired) {
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        onDelete()
                    },

                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }

        }
    }
}

@Composable
fun ItemDetails(
    Film: Film, modifier: Modifier = Modifier
)
{
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor =  MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            ItemDetailsRow(
                labelResID = R.string.judul,
                itemDetail = Film.judul,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )

            ItemDetailsRow(
                labelResID = R.string.genre,
                itemDetail = Film.genre,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
            ItemDetailsRow(
                labelResID = R.string.tahun_rilis,
                itemDetail = Film.tahun_rilis,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            FilmTopAppBar(title = stringResource(DetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditItem(uiState.value.detailFilm.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large))

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.edit_Film),
                )

            }
        }, modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody (
            itemDetailsUiState = uiState.value,
            onDelete = {
                coroutineScope.launch{
                    viewModel.deleteItem()
                    navigateBack()
                }
            }, modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
    }
}





@Composable
fun ItemDetailsRow (
    @StringRes labelResID : Int, itemDetail: String, modifier: Modifier = Modifier
)
{
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier : Modifier = Modifier
){
    AlertDialog(onDismissRequest = { /*Do nothing */ },
        title = { Text(stringResource(R.string.perhatian )) },
        text = { Text(stringResource(R.string.delete)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource( R.string.no))
            }
        },

        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))


            }
        }
    )
}

