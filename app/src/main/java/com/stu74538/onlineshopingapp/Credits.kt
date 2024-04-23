package com.stu74538.onlineshopingapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val content = "Text: Jon Gouspy\n" +
        "Images/Illustrations: Jon Gouspy\n" +
        "Music/Sound Effects: Jon Gouspy\n" +
        "Video Footage/Animation: Jon Gouspy"

val devlopment = "Programming: Jon Gouspy\n" +
        "UI/UX Design: Jon Gouspy\n" +
        "Game Design: Jon Gouspy\n" +
        "Quality Assurance: Jon Gouspy"

const val legal = "All rights reserved. No part of this work may be reproduced or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording, or by any information storage or retrieval system, without the prior written permission of the publishers, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law."

@Composable
fun Credits(navController: NavController, viewModel: SharedViewModel) {
    Scaffold(
        topBar = { AppBar(navController, viewModel = viewModel) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        CreditsBody(innerPadding)
    }
}

@Composable
fun CreditsBody(innerPadding: PaddingValues) {
    Column (
        modifier = Modifier
            .padding(innerPadding)
            .padding(start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState()),
    ){
        Text("CREDIT PAGE", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(bottom = 40.dp, top = 20.dp))

        Text("Last update April 22, 2024", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(bottom = 60.dp))

        Text("Content Creation:", fontWeight = FontWeight.Bold, fontSize = 23.sp, modifier = Modifier.padding(bottom = 10.dp))
        Text(content.trim(), fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 25.dp))

        Text("Development:", fontWeight = FontWeight.Bold, fontSize = 23.sp, modifier = Modifier.padding(bottom = 10.dp))
        Text(devlopment.trim(), fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(bottom = 25.dp))

        Text("Legal:", fontWeight = FontWeight.Bold, fontSize = 23.sp, modifier = Modifier.padding(bottom = 10.dp))
        Text(legal.trim(), fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 25.dp))
    }
}