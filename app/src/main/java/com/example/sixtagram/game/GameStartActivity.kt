package com.example.sixtagram.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class GameStartActivity : AppCompatActivity() {

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamestart)
        initNavigation()

        val btSt = findViewById<Button>(R.id.button1)
        val btLv = findViewById<Button>(R.id.button2)
        val btm = findViewById<Button>(R.id.button3)
        val btd = findViewById<Button>(R.id.button4)
        var numsize: String = getString(R.string.game_numsize_25) // "1~25" "1~50"
        var mode: String = getString(R.string.game_mode_easy) // "이지" "노멀" "하드" "지옥" "Easy" "Normal" "Hard" "Hell"

        btSt.setOnClickListener {
            val intent2 = Intent(this, GameMainActivity::class.java)
            intent2.putExtra("numsize", numsize)
            intent2.putExtra("mode", mode)
            startActivity(intent2)
        }

        btLv.setOnClickListener {
            if (numsize == getString(R.string.game_numsize_25)) {
                numsize = getString(R.string.game_numsize_50)
            } else if (numsize == getString(R.string.game_numsize_50)) {
                numsize = getString(R.string.game_numsize_25)
            }
            btLv.setText("${getString(R.string.game_numsize)}${numsize}")
        }

        btm.setOnClickListener {
            when (mode) {
                getString(R.string.game_mode_easy) -> {
                    mode = getString(R.string.game_mode_normal)
                }

                getString(R.string.game_mode_normal) -> {
                    mode = getString(R.string.game_mode_hard)
                }

                getString(R.string.game_mode_hard) -> {
                    mode = getString(R.string.game_mode_hell)
                }

                getString(R.string.game_mode_hell) -> {
                    mode = getString(R.string.game_mode_easy)
                }
            }
            btm.setText("${getString(R.string.game_game_difficulty)} ${mode} ${getString(R.string.game_mode)}")
        }
        btd.setOnClickListener {
            val intent2 = Intent(this, GameDescriptionActivity::class.java)
            startActivity(intent2)
        }
    }


    private fun initNavigation() = with(bottomNav) {

        selectedItemId = R.id.game

        val member = MemberActivity()
        val calendar = CalendarActivity()
        val community = CommunityActivity()

        setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.member -> mBinding(member)
                R.id.calendar -> mBinding(calendar)
                R.id.community -> mBinding(community)
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

// 1. 게임 설명서, 난이도별 설명 페이지 만들기.