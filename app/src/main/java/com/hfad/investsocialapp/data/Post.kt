package com.hfad.investsocialapp.data

import kotlinx.serialization.Serializable
// параметры для поста
@Serializable
data class Post(
    val id: String, val image: String,
    val date: String, val title: String,
    val text: String,
    val categories: List<String>, val likes: Int,
    val dislikes: Int, val comments: Int,
    val owner: String?,
    val name: String,
    val avatar: String,
    val honor: Int,
)
