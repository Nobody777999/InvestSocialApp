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

        )
    )
}