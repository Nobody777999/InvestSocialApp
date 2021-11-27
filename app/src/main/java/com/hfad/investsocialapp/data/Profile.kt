package com.hfad.investsocialapp.data

data class Profile(

    val id: String,
//    val phone: String,
    val firstName: String,
    val lastName: String,
//    val email: String,
//    val gender: String,
//    val title: String,
//    val thumbUrl: String,
    val picture: String,
//    val dateOfBirth: String,
//    val photos: List<String>,
    val following: Int,
    val followers: Int,
    val text: String,
    val posts: List<Post>
)
