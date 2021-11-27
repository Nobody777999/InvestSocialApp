package com.hfad.investsocialapp.data

data class Post(
// параметры для поста
    val id: String, val image: String, val date: String, val title: String, val text: String,
    val categories: List<String>, val likes: Int,  val dislikes: Int, val comments: Int,
    val owner: Profile
)
