package com.hfad.investsocialapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.hfad.investsocialapp.ui.theme.Purple500
import kotlin.math.round


@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel) {


//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//
//
//        contentAlignment = Alignment.TopCenter
//    ) {

    val searchText = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberImagePainter(data = homeViewModel.imgProfilePath) {
                        transformations(RoundedCornersTransformation(40f))
                    }, contentDescription = "logo", modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
                Spacer(Modifier.padding(4.dp))
                SearchView(state = searchText){
                    focusManager.clearFocus()
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = SpaceBetween) {
                homeViewModel.categoryList.forEach { name ->
                    item {
                        CategoryButton(name = name) {

                        }
                        if (homeViewModel.categoryList.last() != name)
                            Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }

        }

        for (i in 1..5) {
            item {
                ColumnItem()
            }
        }
    }


}


//    }


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

@Preview
@Composable
fun showColumnItem() {
    ColumnItem()
}

@Composable
fun CategoryButton(name: String, onCLick: () -> Unit) {
    Button(
        onClick = onCLick,
//        border = BorderStroke(1.dp, Purple500),
        shape = RoundedCornerShape(50),
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Text(text = name)
    }
}

@Composable
fun SearchView(state: MutableState<String>, onCLick: KeyboardActionScope.() -> Unit) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != "") {
                IconButton(
                    onClick = {
                        state.value =
                            ""
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(30),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = Purple500,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardActions = KeyboardActions(onDone =
            onCLick
        )
    )
}


