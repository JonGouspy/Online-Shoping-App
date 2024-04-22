package com.stu74538.onlineshopingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.stu74538.onlineshopingapp.ui.theme.Black
import com.stu74538.onlineshopingapp.ui.theme.Gray

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Gray)
            .padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier.clickable(onClick = { navController.navigate(Routes.Home.route) }),
            text = "Home",
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            modifier = Modifier.clickable(onClick = { navController.navigate(Routes.Home.route) }),
            text = "Search",
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}