package com.iessanalberto.dam2.tareasapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.peluqueriacrud.screens.CitasScreen
import com.example.peluqueriacrud.screens.EditDeleteCitasScreen
import com.example.peluqueriacrud.screens.LoginScreen
import com.example.peluqueriacrud.screens.NewCitasScreen

@Composable
fun AppNavigation() {
    // Creamos el controlador de navegacion
    val navController = rememberNavController()
    // Creamos el NavHost con el controlador de navegacion y la pantalla inicial
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        // Definimos las pantallas de la app
        composable(route = AppScreens.CitasScreen.route) { CitasScreen(navController = navController) }
        composable(route = AppScreens.NewCitaScreen.route) { NewCitasScreen(navController = navController) }
        composable(route = AppScreens.LoginScreen.route) { LoginScreen(navController = navController) }
        composable(
            route = AppScreens.EditDeleteCitasScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.IntType })
        ) {
                backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")?: -1
            EditDeleteCitasScreen(
                navController = navController,
                id = id)
        }
    }
}