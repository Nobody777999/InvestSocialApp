package com.hfad.investsocialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.LoginView
import com.hfad.investsocialapp.screen.Profile.ProfileView
import com.hfad.investsocialapp.screen.Profile.ProfileViewModel
import com.hfad.investsocialapp.screen.createRecords.CreateRecords
import com.hfad.investsocialapp.screen.createRecords.CreateRecordsViewModel

import com.hfad.investsocialapp.screen.home.HomeView
import com.hfad.investsocialapp.screen.home.HomeViewModel
import com.hfad.investsocialapp.screen.login.LoginViewModel
import com.hfad.investsocialapp.ui.theme.InvestSocialAppTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    val loginViewModel: LoginViewModel by viewModels()
    val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            InvestSocialAppTheme {
                Scaffold {
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
                                loginViewModel = loginViewModel
                            )
                        }
                        composable(NavigationItem.CreateRecords.route) {
                            CreateRecords(navController,CreateRecordsViewModel())

                        }
                        composable(NavigationItem.Profile.route) {
                            ProfileView(navController, ProfileViewModel())
                        }



                    }
                }
            }
        }
    }
}




