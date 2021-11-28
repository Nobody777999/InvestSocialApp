package com.hfad.investsocialapp.data

import kotlinx.serialization.Serializable

// параматры для профиля
@Serializable
data class Profile(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val avatar: String,
//    val following: Int,
//    val followers: Int,
//    val text: String,
    val honor: Int = 2,
//    val posts: List<Post>
)
