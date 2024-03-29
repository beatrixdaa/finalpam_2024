package com.example.finalpam_2024.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalpam_2024.R
import com.example.finalpam_2024.halaman.DestinasiEntry
import com.example.finalpam_2024.halaman.DestinasiHome
import com.example.finalpam_2024.halaman.EntryScreen
import com.example.finalpam_2024.halaman.HomeScreen


@Composable
fun FilmApp(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController=navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmTopAppBar(
    title:String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp:() -> Unit = {}
){
    CenterAlignedTopAppBar(title =  { Text(title) },
        modifier = modifier,
        scrollBehavior=scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        })

}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    )
    {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
            )
        }
        composable(DestinasiEntry.route) {
            EntryScreen(navigateBack = { navController.popBackStack() })
        }
    }
}
