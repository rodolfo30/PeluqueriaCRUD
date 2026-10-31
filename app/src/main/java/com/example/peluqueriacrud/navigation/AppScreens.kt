package com.iessanalberto.dam2.tareasapp.navigation

sealed class AppScreens(val route: String) {
    // Definimos las pantallas de la app
    object CitasScreen : AppScreens(route = "citas_screen")
    object EditDeleteCitasScreen : AppScreens(route = "edit_delete_citas_screen")
    object NewCitaScreen : AppScreens(route = "new_cita_screen")
    object LoginScreen : AppScreens(route = "login_screen")
}