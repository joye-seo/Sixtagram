package com.example.sixtagram

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.calander.CalanderActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //추가 1
    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //추가 2
        initNavigation()
    }

    //추가 3
    private fun initNavigation() = with(bottomNav) {

        //본인 activity 아이디로 변경
        bottomNav.selectedItemId = R.id.member

        //본인 activity 삭제
        val member = MemberActivity()
        val calendar = CalanderActivity()
        val community = CommunityActivity()
        val game = GameActivity()

        //본인 activity 삭제
        this.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.member -> mBinding(member)
                R.id.calendar -> mBinding(calendar)
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