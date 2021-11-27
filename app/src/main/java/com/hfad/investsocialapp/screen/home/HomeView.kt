package com.hfad.investsocialapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.hfad.investsocialapp.data.Post
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.profile.ProfileViewModel
import com.hfad.investsocialapp.ui.theme.Purple500


@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel, profileViewModel: ProfileViewModel) {


//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//
//
//        contentAlignment = Alignment.TopCenter
//    ) {

    val searchText = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val progress = CircularProgressDrawable(LocalContext.current)
    progress.start()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberImagePainter(data = homeViewModel.imgProfilePath) {
                        transformations(RoundedCornersTransformation(40f))
                        placeholder(progress)
                    }, contentDescription = "logo", modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
                Spacer(Modifier.padding(4.dp))
                SearchView(state = searchText) {
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
                PostCard(post = profileViewModel.posts.first(), viewModel = profileViewModel, progress = progress, navController = navController)
            }
        }
    }

    DisposableEffect(key1 = progress) {
        onDispose {
            progress.stop()
        }
    }


}


//    }

@Composable
fun PostCard(post: Post, viewModel: ProfileViewModel, modifier: Modifier = Modifier, progress: CircularProgressDrawable, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 8.dp)
            .clickable(onClick = {
                navController.navigate(NavigationItem.Profile.route) {
                    viewModel.idUser.value = post.owner.id
                    launchSingleTop = true
                }
            }),
        shape = RoundedCornerShape(16.dp),
        elevation = 3.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(percent = 6))
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    rememberImagePainter(data = post.owner.picture) {
                        transformations(RoundedCornersTransformation(40f))
                        placeholder(progress)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )


                Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                    Text(
                        text = post.owner.lastName + " " + post.owner.firstName,
                    )
                    Text(
                        text = post.date,
                        style = MaterialTheme.typography.caption
                    )
                }

            }


            Image(
                rememberImagePainter(data = post.owner.picture) {
                    placeholder(progress)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )



            Row(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)) {
                for (category in post.categories) {
                    KeyWordView(category)

                }
            }
            Text(
                text = post.title,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = post.text,
                style = MaterialTheme.typography.body2
            )
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text =  "Понравилось ${post.likes.toString()}",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Комментарии ${post.likes.toString()}",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .wrapContentWidth(Alignment.End)
                )
            }
        }
    }
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
        keyboardActions = KeyboardActions(
            onDone =
            onCLick
        )
    )
}

@Composable
fun KeyWordView(
    category: String
) {
    Surface(
        modifier = Modifier.padding(end = 6.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.secondary
    ) {
        Row(
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.caption,
                color = Color.White,
                modifier = Modifier.padding(
                    start = 6.dp,
                    end = 6.dp, top = 4.dp, bottom = 4.dp
                )
            )
        }
    }
}




