package com.hfad.investsocialapp.screen.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.investsocialapp.data.Post
import com.hfad.investsocialapp.data.Profile
import com.hfad.investsocialapp.gate.Gate

class HomeViewModel: ViewModel() {
    val gate = Gate.getInstance()
    var dataStore: DataStore<Preferences>? = null


    //Забираем с сервера
    val imgProfilePath = "https://cdn.pixabay.com/photo/2021/02/27/16/25/woman-6055084_960_720.jpg"

    //Тоже должен приходить с сервера
    val categoryList = listOf("Инструмент 1","Инструмент 2","Инструмент 3","Инструмент 4","Инструмент 5","Инструмент 6")

    val searchText = MutableLiveData<String>("")




    //Фэйковые, должны грузиться с сервера
    val posts = mutableListOf(
        // 5 разных постов
        Post(
            "1",
            "https://cdn.pixabay.com/photo/2014/07/06/13/55/calculator-385506__340.jpg",
            "",
            "Важное наблюдение",
            "Срочно изучайте, что я написал",
            listOf("Важное", "Умное", "Интересное"),
            25,
            12,
            14,
            "1",
            "Егор Летов",
            "https://cdn.pixabay.com/photo/2017/10/01/08/41/dusshera-2804587__340.jpg",
            2,
        ),
        Post(
            "2",
            "https://cdn.pixabay.com/photo/2020/02/18/08/35/finance-4858797__340.jpg",
            "",
            "Интересные новости",
            "Новая технология в разработке",
            listOf("Техника", "IT", "Рептилоиды"),
            73,
            12,
            50,
            "1",
            "Марк Цукерберг",
            "https://cdn.pixabay.com/photo/2020/08/08/11/21/avatar-5472749_960_720.jpg",
            24
        ),
        Post(
            "1567",
            "https://cdn.pixabay.com/photo/2014/07/23/15/03/cent-400248__340.jpg",
            "",
            "Презентация",
            "Новая технология в разработке",
            listOf("Что вы делаете", "в моем", "холодильнике"),
            67,
            21,
            39,
            "1",
            "Геннадий Горин",
            "https://cdn.pixabay.com/photo/2020/05/10/07/40/man-5152638__340.jpg",
            12000
        ),

        Post(
            "5467",
            "https://cdn.pixabay.com/photo/2017/05/18/11/04/key-2323278_960_720.jpg",
            "",
            "Медиа",
            "Разработка медиаконтента",
            listOf("Управление", "офис", "менеджмент"),
            567,
            34,
            78,
            "1",
            "Джордж Буш",
            "https://cdn.pixabay.com/photo/2013/12/09/10/02/woman-225690__340.jpg",
            50
        ),
        Post(
            "5",
            "https://cdn.pixabay.com/photo/2018/05/10/12/49/mystery-3387502__340.jpg",
            "",
            "Презентация",
            "Новая технология в разработке",
            listOf("Что вы делаете", "в моем", "холодильнике"),
            67,
            21,
            39,
            "1",
            "Геннадий Горин",
            "https://cdn.pixabay.com/photo/2018/04/07/17/26/startup-3299033__340.jpg",
            7,
        )
    )


}