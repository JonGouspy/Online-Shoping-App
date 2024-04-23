package com.stu74538.onlineshopingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.stu74538.onlineshopingapp.ui.theme.Black
import com.stu74538.onlineshopingapp.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(navController: NavController, viewModel: SharedViewModel){
    Scaffold (
        topBar = { AppBar(navController, viewModel = viewModel) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        ProductBody(innerPadding, viewModel, navController)
    }
}

@Composable
fun ProductBody(innerPadding: PaddingValues, viewModel: SharedViewModel, navController: NavController) {
    var count by remember { mutableIntStateOf(1) }

    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp, top = 40.dp)
        ) {
            AsyncImage(
                model = viewModel.product?.img, contentDescription = null, contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(200.dp)
                    .height(250.dp)
                    .border(1.dp, Black, RoundedCornerShape(5.dp))
            )
            viewModel.product?.name?.let { Text(text = it, color = Black, fontWeight = FontWeight.Bold, modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp, bottom = 15.dp)) }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "${viewModel.product?.price} $    x ${count}", color = Red)
            OutlinedButton(onClick = {
                navController.popBackStack()
                viewModel.product?.let { viewModel.addProduct(it, count)
                } }) {
                Text("Add to basket")
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row {
                Button(onClick = { if (count > 1 ) count-- }) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { count++ }) {
                    Text(text = "+")
                }
            }
        }
    }
}