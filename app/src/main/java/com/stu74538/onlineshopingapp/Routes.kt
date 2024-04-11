package com.stu74538.onlineshopingapp

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object SignUp : Routes("signup")
    data object Home : Routes("home")
    data object Profile : Routes("profile")
    data object Product : Routes("product")
    data object Basket : Routes("basket")
}