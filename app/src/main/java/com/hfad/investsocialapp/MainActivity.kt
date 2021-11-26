package com.hfad.investsocialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.LoginView
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
                        composable(NavigationItem.Home.route){
                            HomeView(navController, homeViewModel)
                        }
                        composable(NavigationItem.Login.route){
                            LoginView(navController = navController, loginViewModel = loginViewModel)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun List() {
    LazyColumn {
        item {
            for (i in 0 until 100) {
                Text(text = "$i")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InvestSocialAppTheme {
        Greeting("Android")
    }
}