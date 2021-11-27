package com.hfad.investsocialapp.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.hfad.investsocialapp.R

@ExperimentalComposeUiApi
@Composable
fun ProfileView(navController: NavController, homeViewModel: ProfileViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.TopStart
    ) {


        Row() {
            Image(painterResource(R.drawable.photo), contentDescription = "photo")
            Column() {
                Text(text = "Name")
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Subscribe")
                }

            }
        }
    }
}
