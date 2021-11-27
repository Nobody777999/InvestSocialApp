package com.hfad.investsocialapp.screen.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.investsocialapp.gate.Gate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel() {

    val user = MutableLiveData("")
    val password = MutableLiveData("")
    val curState = MutableLiveData<State>(State.Default)
    val tryAuthorize = MutableLiveData(false)
    private val gate = Gate()
        // состояния
    sealed class State(){
        object Default: State()
        object Loading: State()
        data class Error(val error: String): State()
        object Done: State()
    }

    fun authorize(user: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            curState.postValue(State.Loading)
            val state = gate.authorize(user, password)
            curState.postValue(state)
        }
    }


    fun createNewUser(user: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            curState.postValue(State.Loading)
            val state = gate.createNewUser(user, password)
            curState.postValue(state)
        }
    }




}