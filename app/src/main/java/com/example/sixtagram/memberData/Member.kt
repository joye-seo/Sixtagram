package com.example.sixtagram.memberData

object Member {
    private var members = mutableListOf(
        MemberData(
            "a",
            "1q",
            "A",
            "인천",
            "ISTJ",
            "독서",
            "여행",
            "https://wonjun3026.tistory.com/",
            "https://wonjun3026.tistory.com/",
            "열심히 하자"
        ),
        MemberData(
            "b",
            "2w",
            "B",
            "서울",
            "INTJ",
            "게임",
            "등산",
            "https://wonjun3026.tistory.com/",
            "https://wonjun3026.tistory.com/",
            "열심히 하자"
        )
    )

    fun createMember(
        addEmail: String,
        addPassword: String,
        addName: String,
        addResidence: String,
        addMbti: String,
        addHobby: String,
        addConcern: String,
        addBlog: String,
        addGithub: String,
        addText: String
    ) {
        var addMember = MemberData(
            addEmail,
            addPassword,
            addName,
            addResidence,
            addMbti,
            addHobby,
            addConcern,
            addBlog,
            addGithub,
            addText
        )
        members.add(addMember)
    }

    fun retrieveMembers(): List<MemberData> {
        return members
    }
}