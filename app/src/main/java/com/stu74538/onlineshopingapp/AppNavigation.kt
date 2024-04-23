package com.stu74538.onlineshopingapp

import android.os.Parcel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {
    val sharedViewModel: SharedViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (auth.currentUser == null) Routes.Login.route else Routes.Home.route,
    ) {
        composable(Routes.Login.route)
        {
            Login(navController)
        }
        composable(Routes.SignUp.route)
        {
            SignUp(navController)
        }
        composable(Routes.Home.route)
        {
            Home(navController, sharedViewModel)
        }
        composable(Routes.ProductDetail.route)
        {
            ProductDetail(navController, sharedViewModel)
        }
        composable(Routes.Profile.route)
        {
            Profile(navController, sharedViewModel)
        }
        composable(Routes.Cart.route)
        {
            Cart(navController, sharedViewModel)
        }
        composable(Routes.Credits.route)
        {
            Credits(navController, sharedViewModel)
        }
    }
}