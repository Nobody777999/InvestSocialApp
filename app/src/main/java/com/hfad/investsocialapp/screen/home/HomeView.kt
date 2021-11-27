package com.hfad.investsocialapp.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.hfad.investsocialapp.screen.Profile.ProfileViewModel


@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize(),


        contentAlignment = Alignment.TopCenter
    ){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            for(i in 1..5) {
                item {
                    ColumnItem()
                }
            }



        }




        Box(
        modifier = Modifier
            .fillMaxSize(),

        contentAlignment = Alignment.BottomCenter
    ){
    Row(

        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,


      // horizontalArrangement = Arrangement.Center
    ) {
        Button(modifier = Modifier
            .weight(1f),shape = RectangleShape,onClick = {/*TODO*/ })
        {
            Text(text = "Главная")

        }

        Button(modifier = Modifier
            .weight(1f), shape = RectangleShape,onClick = {
            /*TODO*/ })
        {
            Text(text = "+")
        }

        Button(modifier = Modifier
            .weight(1f), shape   = RectangleShape,onClick = {
            /*TODO*/ })

        {
            Text(text = "Профиль")
        }
    }
}
}
}
@Composable
fun ColumnItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color.White)
            .border(1.dp, Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Здесь будет пост", color = Color.Blue)
    }
}