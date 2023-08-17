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
            CommunityData("공지사항", "오늘은목요일", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
            CommunityData("공지사항", "오늘은목요일1", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
            CommunityData("공지사항", "오늘은목요일2", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
            CommunityData("공지사항", "오늘은목요일3", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
            CommunityData("공지사항", "오늘은목요일4", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
            CommunityData("공지사항", "오늘은목요일5", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
        )

        communityArrayList.add(
            CommunityData("공지사항", "오늘은목요일3", "목요일이다..주말언제되냐..ㅠㅠ", "", 3),
        )

        val adapter = CommunityAdapter(this, communityArrayList)
        communityListView.adapter = adapter

        btnAdd.setOnClickListener {
            val intent = Intent(this,CommunityAddActivity::class.java)
            startActivity(intent)
                    communityArrayList.add(0,communityArrayList[0])
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