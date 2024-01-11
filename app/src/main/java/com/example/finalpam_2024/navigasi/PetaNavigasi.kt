package com.example.finalpam_2024.navigasi


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.navigation.NavHostController
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.navArgument

import com.example.finalpam_2024.halaman.DestinasiEntry
import com.example.finalpam_2024.halaman.DestinasiHome
import com.example.finalpam_2024.halaman.DetailsDestination
import com.example.finalpam_2024.halaman.DetailsScreen

import com.example.finalpam_2024.halaman.EntryScreen
import com.example.finalpam_2024.halaman.HomeScreen
import com.example.finalpam_2024.halaman.ItemEditDestination
import com.example.finalpam_2024.halaman.ItemEditScreen
import com.example.finalpam_2024.ui.screen.LoginScreen
import com.example.finalpam_2024.ui.screen.SignUpScreen

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
        }
    )

}
@Composable
fun FilmApp(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController=navController)
}




@Composable
fun HostNavigasi(
    navController : NavHostController,

){
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = Modifier
    ) {
        composable("login"){
            LoginScreen(
                navigateToHome = {navController.navigate(DestinasiHome.route)},
                navigateToSignUp = {navController.navigate("signup")}
            )
        }
        composable("signup"){
            SignUpScreen (
                navigateToLogin = {navController.popBackStack()}
            )
        }
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
            )
        }
        composable(DestinasiEntry.route) {
            EntryScreen(navigateBack = { navController.popBackStack() })
        }
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                    navController.navigate("${DetailsDestination.route}/$it")
                },
            )
        }
        composable(DestinasiEntry.route) {
            EntryScreen(navigateBack = { navController.popBackStack() },
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