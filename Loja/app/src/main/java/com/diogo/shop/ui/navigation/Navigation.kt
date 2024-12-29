package com.diogo.shop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diogo.shop.ui.screens.Home
import com.diogo.shop.ui.screens.Login
import com.diogo.shop.ui.screens.Register

@Composable
fun Nav(navController: NavHostController, startPage: String){
    NavHost(
        navController = navController,
        startDestination = startPage
    ) {
        composable("login") { Login(navController) }
        composable("register") { Register(navController) }
        composable("home") { Home(navController) }
    }
}