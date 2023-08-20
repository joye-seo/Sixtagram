package com.example.sixtagram.memberData

object Member {
    internal var members = mutableListOf<MemberData>(
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
            "https://avatars.githubusercontent.com/u/139086025?v=4"
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
            "https://avatars.githubusercontent.com/u/104261048?v=4"
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
            "https://avatars.githubusercontent.com/u/139090280?v=4"
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
            "https://avatars.githubusercontent.com/u/91948969?v=4"
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
            "https://avatars.githubusercontent.com/u/138475088?v=4"
        ),
        MemberData(
            "4",
            " ",
            " ",
            " ",
            " ",
            " ",
            "https://tistory.com/",
            "https://github.com/",
            " ",
            "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ff13d9219-eec7-4124-b789-396bdbe43179%2FKakaoTalk_20230809_063024964_01.jpg?table=block&id=bf800ccd-274d-4e60-aa4a-162a39224540&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=8cbc65cd-a833-4515-8c5f-0836cea60f66&cache=v2"
        ),
        MemberData(
            "4",
            " ",
            " ",
            " ",
            " ",
            " ",
            "https://tistory.com/",
            "https://github.com/",
            " ",
            "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ff13d9219-eec7-4124-b789-396bdbe43179%2FKakaoTalk_20230809_063024964_01.jpg?table=block&id=bf800ccd-274d-4e60-aa4a-162a39224540&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=8cbc65cd-a833-4515-8c5f-0836cea60f66&cache=v2"

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

    ) {
        val updateMember =
            MemberData(password, name, residence, mbti, hobby, concern, blog, github, text, image)
        members[index] = updateMember
    }

    fun deleteMember(index: Int) {
        members.removeAt(index)
    }

    fun retrieveMembers(): List<MemberData> {
        return members
    }
}