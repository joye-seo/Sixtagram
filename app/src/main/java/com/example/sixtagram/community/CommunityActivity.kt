package com.example.sixtagram.community

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.game.GameStartActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommunityActivity : AppCompatActivity() {

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }
    lateinit var communityAdapter: CommunityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)
        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_community_add)


        initNavigation()

        val communityListView = findViewById<ListView>(R.id.listview_community)

        val communityArrayList = arrayListOf(
            CommunityData(
                "공지사항",
                "오늘은목요일",
                "목요일이다..주말언제되냐..ㅠ목요일이다..주말언제되냐..ㅠㅠ목요일이다..주말언제되냐..ㅠㅠ목요일이다..주말언제되냐..ㅠㅠ목요일이다..주말언제되냐..ㅠㅠ목요일이다..주말언제되냐..ㅠㅠ목요일이다..주말언제되냐..ㅠㅠㅠ",
                "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F06eb8522-8536-45fe-b868-a619d4fe70c7%2FR0005627.jpg?table=block&id=d70fd7d5-c9b1-4df7-9023-a08f7918bcc0&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=8cbc65cd-a833-4515-8c5f-0836cea60f66&cache=v2",
                3
            ),
            CommunityData(
                "공지사항",
                "오늘은목요일1",
                "목요일이다..주말언제되냐..ㅠㅠ",
                "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0438542d-f587-42f1-a207-a4135be33fee%2FKakaoTalk_20230808_091904046.jpg?table=block&id=0e5fa1c5-ba4e-4a7f-b36f-eba35b940a20&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=8cbc65cd-a833-4515-8c5f-0836cea60f66&cache=v2",
                3
            ),
            CommunityData(
                "공지사항",
                "오늘은목요일2",
                "목요일이다..주말언제되냐..ㅠㅠ",
                "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ff13d9219-eec7-4124-b789-396bdbe43179%2FKakaoTalk_20230809_063024964_01.jpg?table=block&id=bf800ccd-274d-4e60-aa4a-162a39224540&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=8cbc65cd-a833-4515-8c5f-0836cea60f66&cache=v2",
                3
            ),
            CommunityData(
                "공지사항",
                "오늘은목요일3",
                "목요일이다..주말언제되냐..ㅠㅠ",
                "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0438542d-f587-42f1-a207-a4135be33fee%2FKakaoTalk_20230808_091904046.jpg?table=block&id=0e5fa1c5-ba4e-4a7f-b36f-eba35b940a20&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=8cbc65cd-a833-4515-8c5f-0836cea60f66&cache=v2",
                3
            ),
            CommunityData("공지사항", "오늘은목요일4", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
            CommunityData("공지사항", "오늘은목요일5", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
        )

        val adapter = CommunityAdapter(this, communityArrayList)
        communityListView.adapter = adapter

        btnAdd.setOnClickListener {
            val intent = Intent(this, CommunityAddActivity::class.java)
            startActivity(intent)
            communityArrayList.add(0, communityArrayList[0])
//            communityAdapter.notifyDataSetChanged()
        }

        //아이템 클릭 시 나오는 결과 값
        communityListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CommunityDetailActivity::class.java)
            intent.putExtra("communityData", communityArrayList[position])
            startActivity(intent)
        }

    }


    private fun initNavigation() = with(bottomNav)
    {
        selectedItemId = R.id.community

        val member = MemberActivity()
        val calendar = CalendarActivity()
        val game = GameStartActivity()

        setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.member -> mBinding(member)
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
}