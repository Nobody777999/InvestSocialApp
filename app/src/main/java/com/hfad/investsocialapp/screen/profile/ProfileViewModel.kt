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


        ),
        Post(
            "2",
            "https://cdn.pixabay.com/photo/2020/08/08/11/21/avatar-5472749_960_720.jpg",
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
                "https://cdn.pixabay.com/photo/2020/02/18/08/35/finance-4858797__340.jpg",
                7,
                4,
                "я поставил соус для барбекю на полку прямо как настоящий человек",
                listOf()
            )


        ),
        Post(
            "3",
            "https://cdn.pixabay.com/photo/2020/05/10/07/40/man-5152638__340.jpg",
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
                "https://cdn.pixabay.com/photo/2014/07/23/15/03/cent-400248__340.jpg",
                7,
                4,
                "Слово не воробей, вообще ничто не воробей, кроме воробья",
                listOf()
            )
        ),

        Post(
            "4",
            "https://cdn.pixabay.com/photo/2013/12/09/10/02/woman-225690__340.jpg",
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
                "https://cdn.pixabay.com/photo/2017/05/18/11/04/key-2323278_960_720.jpg",
                7687,
                6786,
                "В детстве я хотел стать космонавтом, но надо было много учиться, поэтому я стал президентом.",
                listOf()
            )
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
            Profile(
                "127",
                "Геннадий",
                "Горин",
                "https://cdn.pixabay.com/photo/2018/04/07/17/26/startup-3299033__340.jpg",
                7,
                4,
                "Слово не воробей, вообще ничто не воробей, кроме воробья",
                listOf()
            )
        )
    )
}