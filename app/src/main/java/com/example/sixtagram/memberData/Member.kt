package com.example.sixtagram.memberData

object Member {
    private var members = mutableListOf<MemberData> (
        MemberData(
            "1",
            "권민석",
            "경기",
            "ISFP",
            "식물 키우기",
            "식물 돌보기",
            "https://coding-martinkwon.tistory.com/",
            "https://github.com/MartinKwon94",
            "우리조에서 나만 못하는듯 ㅠ_ㅠ",
            "www.naver.com"
        ),
        MemberData(
            "2",
            "서수현",
            "서울",
            "INFP",
            "롤경기 보기",
            "티원우승..ㅠㅠ",
            "https://joye.tistory.com/",
            "https://github.com/joye-seo",
            "우리조가 프로젝트 제일 잘한 듯 ㅎ_ㅎ",
            "www.naver.com"
        ),
        MemberData(
            "3",
            "이성진",
            "서울",
            "INFP",
            "핸드폰게임 CoC",
            "티원이 kt잡는지가 관심사 최근",
            "https://velog.io/@asdsad8664",
            "https://github.com/asdsad86642/",
            "열심히할게요!",
            "www.naver.com"
        ),
        MemberData(
            "4",
            "조원준",
            "인천",
            "ISTJ",
            "게임과 독서",
            "여행",
            "https://wonjun3026.tistory.com/",
            "https://github.com/wonjun3026",
            "아직 코딩 실력이 많이 부족하지만 열심히 실력을 쌓아서 다 같이 가고 싶은 회사에 취업합시다. ㅎㅎ.",
            "www.naver.com"
        ),
        MemberData(
            "5",
            "이동규",
            "경기",
            "INFP",
            "산책, 롤,  짤방수집",
            "롤 솔랭",
            "https://velog.io/@dklee1619",
            "https://github.com/dklee1619",
            "육캔두잇 화이팅!!!!!",
            "www.naver.com"

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
        addText: String,
        addImage: String
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
            addText,
            addImage
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
        image: String,
        index: Int

    ){
        val updateMember = MemberData(password, name, residence, mbti, hobby, concern, blog, github, text, image)
        members[index] = updateMember
    }
    fun deleteMember(index: Int){
        members.removeAt(index)
    }

    fun retrieveMembers(): List<MemberData> {
        return members
    }
}