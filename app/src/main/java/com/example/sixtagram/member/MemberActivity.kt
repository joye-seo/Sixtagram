package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MemberActivity : AppCompatActivity() {

//    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        val linearLayout4 = findViewById<LinearLayout>(R.id.linearLayout4)
        linearLayout4.setOnClickListener {
            val intent = Intent(this, MemberDetailActivity::class.java)
            startActivity(intent)
        }



        val listView1 = findViewById<ListView>(R.id.listView1)
        val Adapter = MemUserAdap(this, UserList)

        listView1.adapter = Adapter

        listView1.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedUser = UserList[position] // 선택한 사용자 정보 가져오기
                val intent = Intent(this, MemberDetailActivity::class.java)
                intent.putExtra("MemUser", selectedUser) // 선택한 사용자 정보를 인텐트에 추가
                startActivity(intent)
            }


//            }
        initNavigation()
    }


    private fun initNavigation() = with(bottomNav) {
        selectedItemId = R.id.member

        val com = CommunityActivity()
        val calendar = CalendarActivity()
        val game = GameStartActivity()

        setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.community -> mBinding(com)
                R.id.calendar -> mBinding(calendar)
                R.id.game -> mBinding(game)
            }
            true
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun mBinding(secondActivity: Any) {
        startActivity(Intent(this, secondActivity::class.java))
        finish()
    }

    var UserList = arrayListOf<MemUser>(
        MemUser(
            R.drawable.member_profile_msk,
            name = "권민석",
            email = "mindori9097@gmail.com",
            regidence = "경기",
            mbti = "ISFP",
            hobby = "식물 키우기",
            interest = "식물 돌보기",
            blog = "https://coding-martinkwon.tistory.com/",
            github = "https://github.com/MartinKwon94",
            comment = "우리조에서 나만 못하는듯 ㅠ_ㅠ"
        ),
        MemUser(
            R.drawable.member_profile_shs,
            name = "서수현",
            email = "ssu1019@naver.com",
            regidence = "서울",
            mbti = "INFP",
            hobby = "롤경기 보기",
            interest = "티원우승..ㅠㅠ",
            blog = "https://joye.tistory.com/",
            github = "https://github.com/joye-seo",
            comment = "우리조가 프로젝트 제일 잘한 듯 ㅎ_ㅎ"
        ),
        MemUser(
            R.drawable.member_profile_sjl,
            name = "이성진",
            email = "asdsad86642@gmail.com",
            regidence = "서울",
            mbti = "INFP",
            hobby = "핸드폰게임 CoC",
            interest = "티원이 kt잡는지가 관심사 최근",
            blog = "https://velog.io/@asdsad8664",
            github = "https://github.com/asdsad86642/",
            comment = "열심히할게요!"
        ),
        MemUser(
            R.drawable.member_profile_wjc,
            name = "조원준",
            email = "wonjun3026@naver.com",
            regidence = "인천",
            mbti = "ISTJ",
            hobby = "게임과 독서",
            interest = "여행",
            blog = "https://wonjun3026.tistory.com/",
            github = "https://github.com/wonjun3026",
            comment = "아직 코딩 실력이 많이 부족하지만 열심히 실력을 쌓아서 다 같이 가고 싶은 회사에 취업합시다. ㅎㅎ."
        ),
        MemUser(
            R.drawable.member_profile_dkl,
            name = "이동규",
            email = "dklee1619@naver.com",
            regidence = "경기",
            mbti = "INFP",
            hobby = "산책, 롤,  짤방수집",
            interest = "롤 솔랭",
            blog = "https://velog.io/@dklee1619",
            github = "https://github.com/dklee1619",
            comment = "육캔두잇 화이팅!!!!!"
        ),
        MemUser(
            R.drawable.member_profile_msk,
            name = "권민석",
            email = "mindori9097@gmail.com",
            regidence = "경기",
            mbti = "ISFP",
            hobby = "식물 키우기",
            interest = "식물 돌보기",
            blog = "https://coding-martinkwon.tistory.com/",
            github = "https://github.com/MartinKwon94",
            comment = "잘부탁드려요."
        ),
        MemUser(
            R.drawable.member_profile_msk,
            name = "권민석",
            email = "mindori9097@gmail.com",
            regidence = "경기",
            mbti = "ISFP",
            hobby = "식물 키우기",
            interest = "식물 돌보기",
            blog = "https://coding-martinkwon.tistory.com/",
            github = "https://github.com/MartinKwon94",
            comment = "잘부탁드려요."
        ),
        MemUser(
            R.drawable.member_profile_msk,
            name = "권민석",
            email = "mindori9097@gmail.com",
            regidence = "경기",
            mbti = "ISFP",
            hobby = "식물 키우기",
            interest = "식물 돌보기",
            blog = "https://coding-martinkwon.tistory.com/",
            github = "https://github.com/MartinKwon94",
            comment = "잘부탁드려요."
        )
    )

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        setContentView(R.layout.activity_member)

}

