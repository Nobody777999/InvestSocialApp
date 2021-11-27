package com.hfad.investsocialapp.screen.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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
    var dataStore: DataStore<Preferences>? = null
    val idProfile = stringPreferencesKey("idProfile")
    private val gate = Gate.getInstance()

    sealed class State(){
        object Default: State()
        object Loading: State()
        data class Error(val error: String): State()
        data class Done(val id: String): State()
    }

    fun authorize(user: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            curState.postValue(State.Loading)
            val state = gate.authorize(user, password)
            if (state is State.Done){
                dataStore!!.edit { preferences ->
                    preferences[idProfile] = state.id
                }
            }
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