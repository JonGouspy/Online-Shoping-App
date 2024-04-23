package com.stu74538.onlineshopingapp

import androidx.collection.intListOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
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
        .padding(start = 20.dp, end = 20.dp, top = 30.dp)
    ) {
        ProfileInfo(navController)
        Historic()
    }
}

@Composable
fun ProfileInfo(navController: NavController) {
    auth.currentUser.let {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = it?.photoUrl ?: dark_account_logo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {
                    Firebase.auth.signOut()
                    navController.navigate(Routes.Login.route)
                }) {
                    Text(text = "LOG OUT")
                }
            }

            Column (modifier = Modifier.padding(start = 15.dp, top = 20.dp)){
                val name:List<String> = it?.displayName?.split(" ", limit = 2) ?: listOf("None", "None")

                Text(text = "Name:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 2.dp))
                Text(name.first(), fontStyle = FontStyle.Italic, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = "Name:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 2.dp))
                Text(name.last(), fontStyle = FontStyle.Italic, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@Composable
fun Historic() {

}