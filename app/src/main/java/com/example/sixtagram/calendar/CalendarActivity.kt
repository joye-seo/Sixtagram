package com.example.sixtagram.calendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sixtagram.R
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CalendarActivity : AppCompatActivity() {

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    lateinit var mRvDiary: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calendar)

        initNavigation()


        mRvDiary = findViewById(R.id.recycler_diary)

        val btnBlueWrite: FloatingActionButton = findViewById(R.id.btn_blue_write)
        btnBlueWrite.setOnClickListener {


        }
    }

    private fun initNavigation() = with(bottomNav) {

        selectedItemId = R.id.calendar

        val member = MemberActivity()
        val community = CommunityActivity()
        val game = GameStartActivity()

        setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.member -> mBinding(member)
                R.id.community -> mBinding(community)
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
