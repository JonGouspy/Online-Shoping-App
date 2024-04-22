package com.stu74538.onlineshopingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stu74538.onlineshopingapp.R.drawable.dark_account_logo
import com.stu74538.onlineshopingapp.ui.theme.Black
import com.stu74538.onlineshopingapp.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController, viewModel: SharedViewModel) {
    Scaffold(
        topBar = { AppBar(navController, hiddeLogo = true, viewModel = viewModel) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        ProfileBody(innerPadding, navController, viewModel.user)
    }
}

@Composable
fun ProfileBody(innerPadding: PaddingValues, navController: NavController, user: User?) {
    Column(modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ) {
        ProfileInfo(user = user)
        Historic(navController)
    }
}

@Composable
fun ProfileInfo(user: User?) {
    user?.let {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = it.img,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .border(1.dp, Black, RoundedCornerShape(5.dp))
            )
            Column {
                Text(text = "Nom: ${it.firstName} ${it.lastName}")
                Text(text = "Adresse: ${it.addressNumber}, ${it.street}, ${it.city}, ${it.zipcode}")
                // Add more user information as needed
            }
        }
    }
}

@Composable
fun Historic(navController: NavController) {
    Button(onClick = {
        Firebase.auth.signOut()
        navController.navigate(Routes.Login.route)
    }) {
        Text(text = "LOG OUT")
    }
}