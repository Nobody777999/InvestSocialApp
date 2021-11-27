package com.hfad.investsocialapp.screen.create_record

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@ExperimentalComposeUiApi
@Composable

fun CreateNewRecordView(navController: NavController, createNewRecordViewModel: CreateNewRecordViewModel){
    Box( modifier = Modifier
        .fillMaxSize(),
    ){
        Row() {
            Text(text = "CreateRecord")
        }

    }


}