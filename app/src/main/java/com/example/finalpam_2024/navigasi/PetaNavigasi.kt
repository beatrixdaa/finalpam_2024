package com.example.finalpam_2024.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.finalpam_2024.halaman.DestinasiEntry
import com.example.finalpam_2024.halaman.DestinasiHome
import com.example.finalpam_2024.halaman.DetailsDestination
import com.example.finalpam_2024.halaman.DetailsScreen
import com.example.finalpam_2024.halaman.EntryFilmScreen
import com.example.finalpam_2024.halaman.HomeScreen
import com.example.finalpam_2024.halaman.ItemEditDestination
import com.example.finalpam_2024.halaman.ItemEditScreen


@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination =DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                    navController.navigate("${DetailsDestination.route}/$it")
                },
            )
        }
        composable(DestinasiEntry.route) {
            EntryFilmScreen(navigateBack = { navController.popBackStack() },
            )
        }
        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.FilmIdArg)  {
                type = NavType.IntType
            })
        ){
            DetailsScreen(
                navigateBack = {navController.popBackStack()},
                navigateToEditItem = {
                    navController.navigate("${ItemEditDestination.route}/$it")
                }
            )
        }
        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ){
            ItemEditScreen(navigateBack = { navController.popBackStack()}, onNavigateUp = { navController.navigateUp() })
        }
    }

}
