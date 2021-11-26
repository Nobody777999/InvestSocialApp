package com.hfad.investsocialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.hfad.investsocialapp.screen.LoginView
import com.hfad.investsocialapp.screen.LoginViewModel
import com.hfad.investsocialapp.ui.theme.InvestSocialAppTheme

class MainActivity : ComponentActivity() {
    
    val loginViewModel: LoginViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            
            InvestSocialAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold {
                    LoginView(navController = navController, loginViewModel = loginViewModel)
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
fun List(){
    LazyColumn{
        item {
            for(i in 0 until 100){
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