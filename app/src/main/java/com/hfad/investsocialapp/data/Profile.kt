package com.hfad.investsocialapp.data

data class Profile(

    val id: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val following: Int,
    val followers: Int,
    val text: String,
    val posts: List<Post>
)
