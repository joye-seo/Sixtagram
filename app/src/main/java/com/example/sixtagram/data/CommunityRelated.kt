package com.example.sixtagram.data

import java.io.Serializable

data class CommunityData(
    val topic : String,
    val title : String,
    val content : String,
    val picture : String,
    val like : Int,
):Serializable

data class CommentData(
    val comment : String,
)