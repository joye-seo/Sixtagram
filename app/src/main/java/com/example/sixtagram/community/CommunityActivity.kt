package com.example.sixtagram.community

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.adapter.CommunityAdapter
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.data.Community
import com.example.sixtagram.data.CommunityData
import com.example.sixtagram.game.GameStartActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommunityActivity : AppCompatActivity() {

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }
    lateinit var communityAdapter: CommunityAdapter
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)
        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_community_add)
        val communityArrayList = Community.communityArrayList

        communityAdapter = CommunityAdapter(this, communityArrayList)
        initNavigation()




        val communityListView = findViewById<ListView>(R.id.listview_community)

        val adapter = CommunityAdapter(this, communityArrayList)
        communityListView.adapter = adapter
        adapter.notifyDataSetChanged()

        btnAdd.setOnClickListener {
            val intent = Intent(this, CommunityAddActivity::class.java)
            startActivity(intent)
                adapter.saveItem()
        }

        //아이템 클릭 시 나오는 결과 값
        communityListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CommunityDetailActivity::class.java)
            intent.putExtra("communityData", communityArrayList[position])
            startActivity(intent)
        }


    }

    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

//            if (result.resultCode == RESULT_OK) {
//                val result = result.data?.getStringExtra("addData")
//                Community.communityArrayList.add (result)
//                Community.communityArrayList.add(0, result)
//                communityArrayList.add(0, communityArrayList[0])
////            communityAdapter.notifyDataSetChanged()
//
//            }
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