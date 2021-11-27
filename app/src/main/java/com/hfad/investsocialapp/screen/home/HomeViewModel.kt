package com.hfad.investsocialapp.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.investsocialapp.gate.Gate

class HomeViewModel: ViewModel() {
    val gate = Gate()
    //переменная-ссылка на изображение для фотографии профиня в примере
    val imgProfilePath = "https://cdn.pixabay.com/photo/2021/02/27/16/25/woman-6055084_960_720.jpg"
    // Список инструментов для отображения в ленте
    val categoryList = listOf("Инструмент 1","Инструмент 2","Инструмент 3","Инструмент 4","Инструмент 5","Инструмент 6")
   // перезаписываемый список для строки поиска
    val searchText = MutableLiveData<String>("")




}