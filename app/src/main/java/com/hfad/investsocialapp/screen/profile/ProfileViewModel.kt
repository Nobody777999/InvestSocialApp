package com.hfad.investsocialapp.screen.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.investsocialapp.data.Post
import com.hfad.investsocialapp.data.Profile

class ProfileViewModel : ViewModel() {
    public lateinit var label: String

    val showCurrent = MutableLiveData<Boolean>(true)
    val idUser = MutableLiveData<String>("")

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
            Profile(
                "123",
                "Егор",
                "Летов",
                "https://cdn.pixabay.com/photo/2017/10/01/08/41/dusshera-2804587__340.jpg",
                2,
                4,
                "Я хорош",
                listOf()
            )


        ) ,
                Post(
                "2",
        "https://cdn.pixabay.com/photo/2014/07/06/13/55/calculator-385506__341.jpg",
        "",
        "Интересные новости",
        "Новая технология в разработке",
        listOf("Техника", "IT", "Рептилоиды"),
        73,
        12,
        50,
        Profile(
            "124",
            "Марк",
            "Цукерберг",
            "https://cdn.pixabay.com/photo/2017/10/01/08/41/dusshera-2804587__341.jpg",
            7,
            4,
            "я поставил соус для барбекю на полку прямо как настоящий человек",
            listOf()
        )


    ),
        Post(
        "3",
        "https://cdn.pixabay.com/photo/2014/07/06/13/55/calculator-385506__342.jpg",
        "",
        "Презентация",
        "Новая технология в разработке",
        listOf("Что вы делаете", "в моем", "холодильнике"),
        67,
        21,
        39,
        Profile(
            "125",
            "Геннадий",
            "Горин",
            "https://cdn.pixabay.com/photo/2017/10/01/08/41/dusshera-2804587__342.jpg",
            7,
            4,
            "Слово не воробей, вообще ничто не воробей, кроме воробья",
            listOf()
        )
    ),

                Post(
                "4",
        "https://cdn.pixabay.com/photo/2014/07/06/13/55/calculator-385506__343.jpg",
        "",
        "Медиа",
        "Разработка медиаконтента",
        listOf("Управление", "офис", "менеджмент"),
        567,
        34,
        78,
        Profile(
            "126",
            "Джордж",
            "Буш",
            "https://cdn.pixabay.com/photo/2017/10/01/08/41/dusshera-2804587__343.jpg",
            7687,
            6786,
            "В детстве я хотел стать космонавтом, но надо было много учиться, поэтому я стал президентом.",
            listOf()
        )
    ),
        Post(
            "5",
            "https://cdn.pixabay.com/photo/2014/07/06/13/55/calculator-385506__344.jpg",
            "",
            "Презентация",
            "Новая технология в разработке",
            listOf("Что вы делаете", "в моем", "холодильнике"),
            67,
            21,
            39,
            Profile(
                "127",
                "Геннадий",
                "Горин",
                "https://cdn.pixabay.com/photo/2017/10/01/08/41/dusshera-2804587__344.jpg",
                7,
                4,
                "Слово не воробей, вообще ничто не воробей, кроме воробья",
                listOf()
            )
        )
    )
}