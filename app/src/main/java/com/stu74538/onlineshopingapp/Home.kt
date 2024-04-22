
@file:Suppress("UNUSED_EXPRESSION")

package com.stu74538.onlineshopingapp

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.stu74538.onlineshopingapp.ui.theme.Black
import com.stu74538.onlineshopingapp.ui.theme.Gray
import com.stu74538.onlineshopingapp.ui.theme.Red
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, viewModel: SharedViewModel) {
    Scaffold(
        topBar = { AppBar(navController, viewModel = viewModel) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        HomeBody(navController, viewModel, innerPadding)
    }
}

@Composable
fun HomeBody(navController: NavController, viewModel: SharedViewModel, innerPadding: PaddingValues) {
    var productList by remember { mutableStateOf<List<Product>>(emptyList()) }
    var categories by remember { mutableStateOf<List<String>>(emptyList()) }

    val db = FirebaseDatabase.getInstance()
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(true) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val productsReference = db.reference.child("products")
            productsReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Product>()
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        product?.let {
                            list.add(it)
                        }
                    }
                    productList = list

                    val categorySet = mutableSetOf<String>()
                    for (product in productList) {
                        categorySet.add(product.category!!)
                    }
                    categories = categorySet.toList()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    Column(
        Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
    ) {
        Categories(categories)
        productList.forEach {
            HomeProduct(
                navController, viewModel, it
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun Categories(categories: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .background(Gray)
            .padding(start = 20.dp, end = 20.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        categories.forEach {
            Text(it)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun HomeProduct(navController: NavController, viewModel: SharedViewModel, product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(150.dp)
            .border(1.dp, Black, RoundedCornerShape(5.dp))
            .clickable {
                viewModel.productDetail(product)
                navController.navigate(Routes.ProductDetail.route)
            }
    ) {
        AsyncImage(
            model = product.img,
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
            product.name?.let { Text(text = it, color = Black) }
            Text(text = "${product.price} $", color = Red)
        }
    }
}