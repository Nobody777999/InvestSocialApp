package com.hfad.investsocialapp.screen.comments

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.hfad.investsocialapp.data.Comment

class CommentsViewModel : ViewModel() {

    val comments =
        mutableStateListOf(
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Это хорошо",
                "Micha"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Странные мысли",
                "Гога"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Это кайф",
                "Эксперт"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Ого",
                "Nagibator"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Видно же, что дурак",
                "Whatta"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Ну вполне, почему бы и нет",
                "Кремень"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Верните его в дурку",
                "Stonks"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Заберите у макаки",
                "Менеджер банка"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Хм, давай встретимся",
                "Капут"
            ),
            Comment(
                1,
                "https://cdn.pixabay.com/photo/2016/08/18/11/00/man-1602633_1280.png",
                "Арбузы зимой недорого, +7982343253257, Екатерина, еще есть пармезан",
                "Ленин"
            )
        )




}