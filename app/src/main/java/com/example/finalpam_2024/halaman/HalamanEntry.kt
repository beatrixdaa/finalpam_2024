package com.example.finalpam_2024.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam_2024.R
import com.example.finalpam_2024.model.DetailFilm
import com.example.finalpam_2024.model.EntryViewModel
import com.example.finalpam_2024.model.PenyediaViewModel
import com.example.finalpam_2024.model.UIStateFilm
import com.example.finalpam_2024.navigasi.FilmTopAppBar
import kotlinx.coroutines.launch

object DestinasiEntry: DestinasiNavigasi {
    override  val route ="item_entry"
    override val titleRes = R.string.entry_Film
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryFilmScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)

) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FilmTopAppBar(
                title = stringResource(DestinasiEntry.titleRes),
                canNavigateBack  = true,
                scrollBehavior = scrollBehavior
            )
        }) { innerPadding ->
        EntryFilmBody(
            uiStateFilm = viewModel.uiStateFilm,
            onFilmValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveFilm()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )


    }
}
@Composable
fun EntryFilmBody(
    uiStateFilm: UIStateFilm,
    onFilmValueChange:(DetailFilm) -> Unit,
    onSaveClick: () -> Unit,
    modifier : Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_Large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInputFilm(
            detailFilm = uiStateFilm.detailFilm,
            onValueChange = onFilmValueChange,
            modifier= Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateFilm.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputFilm(
    detailFilm: DetailFilm,
    modifier: Modifier = Modifier,
    onValueChange: (DetailFilm) -> Unit = {},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier ,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = detailFilm.judul,
            onValueChange ={onValueChange(detailFilm.copy(judul=it))},
            label = { Text(stringResource(id = R.string.judul)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailFilm.genre,
            onValueChange ={onValueChange(detailFilm.copy(genre=it))},
            label = { Text(stringResource(id = R.string.genre)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailFilm.tahun_rilis,
            onValueChange ={onValueChange(detailFilm.copy(tahun_rilis =it))},
            label = { Text(stringResource(id = R.string.tahun_rilis)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if(enabled) {
            Text(
                text = stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider (
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
        )

    }
}
