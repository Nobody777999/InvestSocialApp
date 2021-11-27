package com.hfad.investsocialapp.screen.create_record

import android.content.Context
import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.investsocialapp.gate.Gate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class CreateNewRecordViewModel: ViewModel() {
  val gate = Gate.getInstance()
  var dataStore: DataStore<Preferences>? = null
  val createRecord = MutableLiveData(false)

  val categories = MutableLiveData(mutableListOf("IT", "Банки", "Все по немножку", "Важное"))

  val date = Date()


  fun createNewRecord(uri: Uri, context: Context){
      viewModelScope.launch(Dispatchers.IO) {
        try {
          gate.uploadPhoto(uri, context = context)
        }catch (e: Exception){
          print(e.localizedMessage)
        }

      }

  }
}