package com.hfad.investsocialapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.profile.ProfileViewModel

// параметры для поисковой строки
@Composable
fun HomeView(
    navController: NavController,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel
) {


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
//Элементы ленты
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
        // Основная лента новостей
        homeViewModel.posts.forEach {
            item {
                PostCard(
                    post = it,
                    viewModel = profileViewModel,
                    progress = progress,
                    navController = navController,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 8.dp)
                        .clickable(onClick = {
                            navController.navigate(NavigationItem.Profile.route) {
                                profileViewModel.idUser.value = it.owner
                                launchSingleTop = true
                            }
                        })
                )
            }
        }

        item {
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
        }
    }

    DisposableEffect(key1 = progress) {
        onDispose {
            progress.stop()
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




