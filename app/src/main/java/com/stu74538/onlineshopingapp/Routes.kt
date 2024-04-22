package com.stu74538.onlineshopingapp

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object SignUp : Routes("signup")
    data object Home : Routes("home")
    data object Profile : Routes("profile")
    data object ProductDetail : Routes("product-details")
    data object Cart : Routes("cart")
}