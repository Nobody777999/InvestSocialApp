package com.hfad.investsocialapp.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hfad.investsocialapp.R
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.login.LoginViewModel

@ExperimentalComposeUiApi
@Composable
fun LoginView(
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavController,
    isLogin: MutableState<Boolean>
) {

    val curState = loginViewModel.curState.observeAsState()
    val isAuthorize = remember { mutableStateOf(false) }
    val tryAuthorize = loginViewModel.tryAuthorize.observeAsState()
    isLogin.value = false


    Crossfade(targetState = curState.value) { state ->
        when (state) {
            is LoginViewModel.State.Default ->
                LoginSection(loginViewModel)
            LoginViewModel.State.Done -> {
//                loginViewModel.curState.value = LoginViewModel.State.Default
                isAuthorize.value = true
                isLogin.value = true


            }
            is LoginViewModel.State.Error ->
                Text(text = state.error)
            LoginViewModel.State.Loading ->
                LoadingView()
            null -> throw IllegalStateException("Состояние равно null")
        }
    }

    LaunchedEffect(key1 = curState.value) {
        if (isAuthorize.value && curState.value == LoginViewModel.State.Done) {
            navController.navigate(NavigationItem.Home.route) {
                popUpTo(NavigationItem.Login.route){
                    inclusive = true
                }
                launchSingleTop = true
            }
            loginViewModel.curState.value = LoginViewModel.State.Default
            isAuthorize.value = false
        }
    }

    LaunchedEffect(key1 = tryAuthorize.value) {
        if (tryAuthorize.value!!) {
            loginViewModel.authorize(loginViewModel.user.value!!, loginViewModel.password.value!!)
            loginViewModel.tryAuthorize.value = false
        }
    }


}

@ExperimentalComposeUiApi
@Composable
fun LoginSection(loginViewModel: LoginViewModel) {

    val user = loginViewModel.user.observeAsState()
    val password = loginViewModel.password.observeAsState()
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 30.dp)
//                .background(Color.White),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            Image(painterResource(R.drawable.logo), contentDescription = "logo")
//
//        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
        ) {


            Text(
                text = "Авторизация",
                style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = user.value!!, onValueChange = { loginViewModel.user.value = it },
                    label = { Text(text = "Логин") },
                    placeholder = { Text(text = "Логин") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })
                )
                OutlinedTextField(
                    value = password.value!!,
                    onValueChange = { loginViewModel.password.value = it },
                    label = { Text(text = "Пароль") },
                    placeholder = { Text(text = "Пароль") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        loginViewModel.tryAuthorize.value = true
                    })
                )
                Spacer(Modifier.padding(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        loginViewModel.tryAuthorize.value = true
                    }) {
                        Text(text = "Войти")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Регистрация")
                    }

                }
            }

        }
    }
}