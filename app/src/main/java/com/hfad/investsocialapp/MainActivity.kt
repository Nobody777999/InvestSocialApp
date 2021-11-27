package com.hfad.investsocialapp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Signal")

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            SignalApp()
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                // Update all of the system bar colors to be transparent, and use
                // dark icons if we're in light theme
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
            }
        }


    }


    @Composable
    private fun SignalApp() {
        val loginViewModel: LoginViewModel by viewModels()
        val homeViewModel: HomeViewModel by viewModels()
        val createRecordViewModel: CreateNewRecordViewModel by viewModels()
        val profileViewModel: ProfileViewModel by viewModels()

        loginViewModel.dataStore = applicationContext.dataStore
        homeViewModel.dataStore = applicationContext.dataStore
        createRecordViewModel.dataStore = applicationContext.dataStore
        profileViewModel.dataStore = applicationContext.dataStore
        val navController = rememberNavController()
        val fabIcon = remember {
            mutableStateOf(Icons.Default.Add)
        }
        val fabClick = remember{ mutableStateOf({
            navController.navigate(NavigationItem.CreateRecord.route) {
                launchSingleTop = true
            }
        })}

        val isLogin = remember { mutableStateOf(false) }

        InvestSocialAppTheme {
            Scaffold(
                floatingActionButton = {
                    if (isLogin.value) {
                        FloatingActionButton(onClick = fabClick.value
                        ) {
                            Icon(fabIcon.value, contentDescription = "Создать новую")

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
                            ),

                            contentPadding = PaddingValues(horizontal = 50.dp)
                        ) {
                            IconButton(onClick = {
                                navController.navigate(NavigationItem.Home.route) {
                                    launchSingleTop = true
                                }
                            }) {
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Localized description",
                                )
                            }
                            Spacer(Modifier.weight(0.5f, true))
                            IconButton(onClick = {
                                profileViewModel.curProfile.value = true
                                navController.navigate(NavigationItem.Profile.route) {
                                    launchSingleTop = true
                                }
                            }) {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = "Localized description",
                                )
                            }
                        }
                    }
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationItem.Login.route
                ) {
                    composable(NavigationItem.Home.route) {
                        HomeView(navController, homeViewModel, profileViewModel)
                    }
                    composable(NavigationItem.Login.route) {
                        LoginView(
                            navController = navController,
                            loginViewModel = loginViewModel,
                            isLogin = isLogin
                        )
                    }
                    composable(NavigationItem.CreateRecord.route) {
                        CreateNewRecordView(
                            navController = navController,
                            createNewRecordViewModel = createRecordViewModel,
                            fabIcon,
                            fabClick
                        )
                    }
                    composable(NavigationItem.Profile.route) {
                        ProfileView(navController, profileViewModel, homeViewModel)
                    }
                }
            }
        }
    }
}






