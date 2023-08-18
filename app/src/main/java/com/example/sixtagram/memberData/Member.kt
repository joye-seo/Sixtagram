package com.example.sixtagram.memberData

object Member {
    private var members = mutableListOf(
        MemberData(
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
        val addMember = MemberData(
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

    fun updateMember(
        password: String,
        name: String,
        residence: String,
        mbti: String,
        hobby: String,
        concern: String,
        blog: String,
        github: String,
        text: String,
        index: Int
    ){
        val updateMember = MemberData(password, name, residence, mbti, hobby, concern, blog, github, text)
        members[index] = updateMember
    }
    fun deleteMember(index: Int){
        members.removeAt(index)
    }

    fun retrieveMembers(): List<MemberData> {
        return members
    }
}