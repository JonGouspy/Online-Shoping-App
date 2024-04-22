package com.stu74538.onlineshopingapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.stu74538.onlineshopingapp.ui.theme.Black
import com.stu74538.onlineshopingapp.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(navController: NavController, viewModel: SharedViewModel){
    Scaffold (
        topBar = { AppBar(navController, hiddeBasket = true, viewModel = viewModel) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        CartBody(innerPadding, viewModel)
    }
}

@Composable
fun CartBody(innerPadding: PaddingValues, viewModel: SharedViewModel) {
    Column(
        Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        viewModel.cart.forEach {
            CartProduct(it)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    if (viewModel.cart.isNotEmpty()) {
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize().padding(bottom = 10.dp),
            Alignment.BottomCenter
        ) {
            Button(onClick = { }) {
                Text("Pay")
            }
        }
    } else {
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            Alignment.Center
        ) {
            Text("Nothing in cart")
        }
    }
}

@Composable
fun CartProduct(cartItem: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(150.dp)
            .border(1.dp, Black, RoundedCornerShape(5.dp))
    ) {
        AsyncImage(
            model = cartItem.img,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(end = 20.dp)
                .width(120.dp)
                .height(150.dp)
                .border(1.dp, Black, RoundedCornerShape(5.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            cartItem.name?.let { Text(text = it, color = Black) }
            Text(text = "${cartItem.price} $ x ${cartItem.numberOfItem}", color = Red)
        }
    }
}