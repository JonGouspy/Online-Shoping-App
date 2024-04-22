package com.stu74538.onlineshopingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.stu74538.onlineshopingapp.ui.theme.Gray
import kotlinx.coroutines.flow.Flow

@Composable
fun AppBar(navController: NavController, hiddeLogo: Boolean = false, hiddeBasket: Boolean = false, viewModel: SharedViewModel) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Gray)
            .padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.clickable(onClick = { navController.navigate(Routes.Home.route) }),
            text = "OnlineShoppingApp",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Row(
            Modifier.fillMaxHeight().padding(end = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!hiddeLogo) {
                ProfileImage(viewModel.user, navController)
            }
            if (!hiddeBasket) {
                Spacer(Modifier.width(2.dp))
                Image(
                    modifier = Modifier
                        .clickable(onClick = { navController.navigate(Routes.Cart.route) })
                        .width(35.dp)
                        .height(35.dp),
                    painter = painterResource(R.drawable.basket),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ProfileImage(user: User?, navController: NavController, modifier: Modifier = Modifier) {
    val imgUrl = user?.img

    imgUrl?.let {
        Image(
            painter = rememberAsyncImagePainter(imgUrl),
            contentDescription = null,
            modifier = modifier.size(45.dp).clickable { navController.navigate(Routes.Profile.route) },
            contentScale = ContentScale.Crop,
        )
    } ?: run {
        AsyncImage(
            model = "https://thispersondoesnotexist.com/",
            contentDescription = null,
            modifier = modifier.size(45.dp).clickable { navController.navigate(Routes.Profile.route) },
            contentScale = ContentScale.Crop
        )
    }
}