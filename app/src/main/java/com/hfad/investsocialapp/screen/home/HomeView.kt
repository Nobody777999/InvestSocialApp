package com.hfad.investsocialapp.screen.home
import com.hfad.investsocialapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize(),


        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for (i in 1..5) {
                item {
                    ColumnItem()
                }
            }


        }




        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

        }
    }
}

@Composable
fun ColumnItem() {
    Column(
        modifier = Modifier

            .fillMaxWidth()
            .fillMaxHeight()
            .height(30.dp)
            .background(Color.White)
            .border(1.dp, Color.Blue),
        verticalArrangement = Arrangement.Top,


    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)

        ){
            Column(horizontalAlignment = Alignment.Start)
                 {
                Image(
                    painterResource(R.drawable.photo),
                    contentDescription = "profile_photo",
                    modifier = Modifier
                        .padding(8.dp)
                        .width(60.dp)
                        .height(60.dp)
                        .clip(CircleShape)
                )
            }

            Column() {
                Text( modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    text = "Name",
                    fontSize = 30.sp,)
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    fontSize = 20.sp,
                    text = "Rating")
                Row(horizontalArrangement = Arrangement.End) {
                    Button(onClick = { /*TODO*/ }){
                        Text(text = "Подписаться")
                    }
                }
            }
        }
Row (modifier = Modifier
    .background(Color.Red)
    .fillMaxWidth()
    .fillMaxHeight()
) {

    postStructure()
    
}

    }
}
//функция должна принимать имя и рейтинг для отображения fun postStructure(String Name, Int Rating){
@Composable
fun postStructure(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)

    ){
        Column(horizontalAlignment = Alignment.Start)
        {
            Image(
                painterResource(R.drawable.photo),
                contentDescription = "profile_photo",
                modifier = Modifier
                    .padding(8.dp)
                    .width(30.dp)
                    .height(30.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.padding(40.dp))
        Column() {
            Row() {
                Text( modifier = Modifier

                    .padding(8.dp),
                    text = "Name",
                    fontSize = 20.sp,)
                Text(modifier = Modifier

                    .padding(8.dp),
                    fontSize = 15.sp,
                    text = "Rating")
            }


            }
        }
    }



@Preview
@Composable
fun showColumnItem(){
    ColumnItem()
}