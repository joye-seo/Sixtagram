package com.example.sixtagram.data

import java.io.Serializable

data class MemberData(
    var password: String,
    var name: String,
    var residence: String,
    var mbti: String,
    var hobby: String,
    var concern: String,
    var blog: String,
    var github: String,
    var text: String,
    var imageEdit: String
): Serializable
