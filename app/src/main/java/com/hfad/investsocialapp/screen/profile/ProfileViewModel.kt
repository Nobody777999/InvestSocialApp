package com.hfad.investsocialapp.screen.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.investsocialapp.data.Post
import com.hfad.investsocialapp.data.Profile
import com.hfad.investsocialapp.gate.Gate
import com.hfad.investsocialapp.screen.LoadingView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel : ViewModel() {
    var dataStore: DataStore<Preferences>? = null
    val gate = Gate.getInstance()
    val idUser = MutableLiveData("")
    val curState = MutableLiveData<State>(State.Loading)
    val prefIdProfile = stringPreferencesKey("idProfile")
    val curProfile = MutableLiveData(false)





    sealed class State(){
        object Loading: State()
        data class Error(val error: String): State()
        data class Data(val data: Profile): State()
    }


    fun getProfileById(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                curState.postValue(State.Loading)
                val result = gate.getProfileById(id)
                curState.postValue(result)
            }catch (e: Exception){
                curState.postValue(State.Error(e.localizedMessage))
            }
        }
    }

    fun setCurProfile(){
        viewModelScope.launch {
            idUser.value = dataStore!!.data.map {
                it[prefIdProfile] ?: ""
            }.first()
        }
    }










}