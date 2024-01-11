package com.example.finalpam_2024.halaman

import androidx.appcompat.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam_2024.R
import com.example.finalpam_2024.data.Film
import com.example.finalpam_2024.model.HomeViewModel
import com.example.finalpam_2024.model.PenyediaViewModel
import com.example.finalpam_2024.navigasi.FilmTopAppBar


object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

interface DestinasiNavigasi {

    val titleRes: Int
    val route: String
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    navigateToItemEntry: () -> Unit,
    modifier : Modifier = Modifier,
    onDetailClick : (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val searchBarState = remember { mutableStateOf(TextFieldValue("")) }


    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FilmTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large))

            ){
                Icon (
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.entry_Film)
                )
            }
        },
    ) {
            innerPadding  ->
        val uiStateFilm by viewModel.homeUiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            val searchedText = searchBarState.value.text

            BodyHome(
                itemFilm = uiStateFilm.listFilm,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                onFilmClick = onDetailClick
            )
        }
    }
}
@Composable
fun BodyHome(
    itemFilm: List<Film>,
    modifier: Modifier= Modifier,
    onFilmClick: (Int) -> Unit = {}
) {
    Column (
        horizontalAlignment =   Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemFilm.isEmpty()) {
            Text (
                text = stringResource(id = R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListFilm(
                itemFilm = itemFilm,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = {onFilmClick(it.id)}
            )
        }
    }
}
@Composable
fun ListFilm(
    itemFilm: List<Film>,
    modifier: Modifier=Modifier,
    onItemClick: (Film) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,

        ) {
        LazyColumn(modifier = Modifier) {
            items(items = itemFilm, key = { it.id }) { person ->
                DataFilm(
                    Film = person,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clickable { onItemClick(person) }
                )
            }
        }
    }
}




@Composable
fun DataFilm(
    Film: Film,
    modifier: Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = Film.judul,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                )
                Text(
                    text = Film.genre,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = Film.tahun_rilis,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

