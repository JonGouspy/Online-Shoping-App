package com.stu74538.onlineshopingapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route,
    ) {
        composable(route = Routes.Login.route)
        {
            Login(navController = navController)
        }
        composable(route = Routes.SignUp.route)
        {
            SignUp(navController = navController)
        }
        composable(route = Routes.Home.route)
        {
            Home(navController = navController)
        }
        composable(route = Routes.Profile.route)
        {
            Profile(navController = navController)
        }
        composable(route = Routes.Product.route)
        {
            Product(navController = navController)
        }
        composable(route = Routes.Basket.route)
        {
            Basket(navController = navController)
        }
    }
}