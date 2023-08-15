package com.example.sixtagram.community

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.MainActivity
import com.example.sixtagram.R
import com.example.sixtagram.calander.CalanderActivity
import com.example.sixtagram.game.GameActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CommunityActivity : AppCompatActivity() {

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)
        initNavigation()
    }

    private fun initNavigation() = with(bottomNav)
    {
        bottomNav.selectedItemId = R.id.community

        val member = MainActivity()
        val calendar = CalanderActivity()
        val game = GameActivity()

        this.setOnItemSelectedListener { item ->

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