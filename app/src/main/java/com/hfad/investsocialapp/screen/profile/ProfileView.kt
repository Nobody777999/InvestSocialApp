package com.hfad.investsocialapp.screen.profile

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.widget.Space
import androidx.compose.material.*
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Info
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.ui.input.ImeAction
import coil.compose.rememberImagePainter
import com.hfad.investsocialapp.R
import com.hfad.investsocialapp.data.Profile
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.LoadingView
import com.hfad.investsocialapp.screen.create_record.ShowDialog
import com.hfad.investsocialapp.screen.home.HomeViewModel
import com.hfad.investsocialapp.screen.home.PostCard

@ExperimentalComposeUiApi
@Composable
fun ProfileView(

    navController: NavController,
    profileViewModel: ProfileViewModel,
    homeViewModel: HomeViewModel
) {

    val curState = profileViewModel.curState.observeAsState()
    val idProfile = profileViewModel.idUser.observeAsState()
    val curProfile = profileViewModel.curProfile.observeAsState()




    Crossfade(targetState = curState.value) { state ->
        when (state) {
            is ProfileViewModel.State.Data ->
                ProfileCard(state.data, profileViewModel, navController, homeViewModel)
            is ProfileViewModel.State.Error -> Text(text = state.error)
            ProfileViewModel.State.Loading -> LoadingView()
            null -> Text(text = "Нет информации")
        }

    }

    LaunchedEffect(key1 = idProfile.value) {
        if (idProfile.value!!.isEmpty()) {
            profileViewModel.setCurProfile()
        } else {
            profileViewModel.getProfileById(idProfile.value ?: "1")
        }
    }


//    Box(
//        modifier = Modifier
//            .fillMaxSize(), contentAlignment = Alignment.TopStart
//    ) {
//
//
//
//
//
//    }

}

@Composable
fun ProfileCard(
    profile: Profile,
    profileViewModel: ProfileViewModel,
    navController: NavController,
    homeViewModel: HomeViewModel,
) {

    var showDialog = remember{mutableStateOf(false)}
    val context = LocalContext.current
    val curProfile = profileViewModel.curProfile.observeAsState()
    val progress = CircularProgressDrawable(LocalContext.current)
    val buttonText = remember { mutableStateOf("Подписаться") }


    progress.start()
    Column(
        modifier = Modifier

            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
//        verticalArrangement = Arrangement.Top,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Image(
                rememberImagePainter(data = profile.avatar),
                contentDescription = "profile_photo",
                modifier = Modifier
                    .padding(8.dp)
                    .width(60.dp)
                    .height(60.dp)
                    .clip(CircleShape)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = profile.first_name + " " + profile.last_name,
                fontSize = 30.sp,
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontSize = 20.sp,
            text = "Репутация: ${profile.honor}"
        )

        if (!curProfile.value!!){
            Button(onClick = {
                if (buttonText.value == "Подписаться") {
                    Toast.makeText(
                        context,
                        "Вы успешно подписались",
                        Toast.LENGTH_SHORT
                    ).show()
                    buttonText.value = "Отписаться"
                } else if (buttonText.value == "Отписаться") {
                    Toast.makeText(
                        context,
                        "Вы успешно отписались",
                        Toast.LENGTH_SHORT
                    ).show()
                    buttonText.value = "Подписаться"
                }
            },
                modifier = Modifier.padding(8.dp)) {
                Text(text = buttonText.value)
            }
        }else{
            Button(onClick = {
                showDialog.value = true


            },
                modifier = Modifier.padding(8.dp)) {
                Text(text = "Обучение")
            }
        }



        Spacer(modifier = Modifier.padding(8.dp))

        if (homeViewModel.posts.isNotEmpty()) {
            LazyColumn {
                item {
                    Text(
                        text = "Посты пользователя",
                        fontSize = 30.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                item { Spacer(modifier = Modifier.padding(8.dp)) }
                homeViewModel.posts.forEach {
                    item {
                        PostCard(
                            post = it,
                            viewModel = profileViewModel,
                            progress = progress,
                            navController = navController,
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 8.dp)
                        )
                    }


                }
                item { Spacer(modifier = Modifier.padding(36.dp)) }
            }
        }


//        }

    }

//    DisposableEffect(key1 = curProfile.value){
//
//        onDispose { profileViewModel.curProfile.value = false }
//
//    }

    if (showDialog.value) {
        ShowDialog(show = showDialog)
    }

//    if (showDialog.value == true){
//        ShowDialogLearn(mutableStateOf(true))
//    }
@Composable
fun ShowDialogL(show: MutableState<Boolean>)  {

    AlertDialog(onDismissRequest = { show.value = false },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(modifier = Modifier.fillMaxWidth(), onClick = { show.value = false }) {
                    Icon(Icons.Filled.Check,contentDescription = "Next")
                    showDialog.value = false
                }
            }

        },
        title = { Text(text = "Обучение") },
        text = { Text(text = "Текст обучения") }
    )


}
}
//функция должна принимать имя и рейтинг для отображения fun postStructure(String Name, Int Rating){
@Composable
fun postStructure() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)

    ) {
        Column(horizontalAlignment = Alignment.Start)
        {
//            Image(
//                painterResource(R.drawable.photo),
//                contentDescription = "profile_photo",
//                modifier = Modifier
//                    .padding(8.dp)
//                    .width(30.dp)
//                    .height(30.dp)
//                    .clip(CircleShape)
//            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Image(
                    painterResource(R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(8.dp)
                        .width(400.dp)
                        .height(400.dp)
                        .clip(RectangleShape)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
            }

        }
//        Spacer(modifier = Modifier.padding(40.dp))
//        Column() {
//            Row() {
//                Text(
//                    modifier = Modifier
//
//                        .padding(8.dp),
//                    text = "Name",
//                    fontSize = 20.sp,
//                )
//                Text(
//                    modifier = Modifier
//
//                        .padding(8.dp),
//                    fontSize = 15.sp,
//                    text = "Rating"
//                )
//            }
//
//
//        }
    }
}

