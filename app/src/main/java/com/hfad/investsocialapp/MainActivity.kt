package com.hfad.investsocialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.LoginView
import com.hfad.investsocialapp.screen.profile.ProfileView
import com.hfad.investsocialapp.screen.profile.ProfileViewModel
import com.hfad.investsocialapp.screen.create_record.CreateNewRecordView
import com.hfad.investsocialapp.screen.create_record.CreateNewRecordViewModel

import com.hfad.investsocialapp.screen.home.HomeView
import com.hfad.investsocialapp.screen.home.HomeViewModel
import com.hfad.investsocialapp.screen.login.LoginViewModel
import com.hfad.investsocialapp.ui.theme.InvestSocialAppTheme


@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    val loginViewModel: LoginViewModel by viewModels()
    val homeViewModel: HomeViewModel by viewModels()
    val createRecordViewModel: CreateNewRecordViewModel by viewModels()
    val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLogin = remember { mutableStateOf(false) }
            val navController = rememberNavController()
            InvestSocialAppTheme {
                Scaffold(
                    floatingActionButton = {
                        if (isLogin.value) {
                            FloatingActionButton(onClick = { navController.navigate(NavigationItem.CreateRecord.route){
                                launchSingleTop = true
                            } }) {
                                Icon(Icons.Filled.Add, contentDescription = "Создать новую")

                            }
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,

                    bottomBar = {
                        if (isLogin.value) {
                            BottomAppBar(

                                cutoutShape = MaterialTheme.shapes.small.copy(
                                    CornerSize(percent = 50)
                                )
                            ) {
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationItem.Login.route
                    ) {
                        composable(NavigationItem.Home.route) {
                            HomeView(navController, homeViewModel)
                        }
                        composable(NavigationItem.Login.route) {
                            LoginView(
                                navController = navController,
                                loginViewModel = loginViewModel,
                                isLogin = isLogin
                            )
                        }
                        composable(NavigationItem.CreateRecord.route) {
                            CreateNewRecordView(navController = navController, createNewRecordViewModel = createRecordViewModel)
                        }
                        composable(NavigationItem.Profile.route) {
                            ProfileView(navController, profileViewModel)
                        }


                    }
                }
            }
        }
    }
}




