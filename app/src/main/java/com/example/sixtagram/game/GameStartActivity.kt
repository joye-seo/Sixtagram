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
        var numsize: String = "1~25" // "1~25" "1~50"
        var mode: String = "노멀 모드" // "easy" "normal"

        btSt.setOnClickListener {
            val intent2 = Intent(this, GameMainActivity::class.java)
            intent2.putExtra("numsize", numsize)
            intent2.putExtra("mode", mode)
            startActivity(intent2)
        }

        btLv.setOnClickListener {
            if (numsize == "1~25") {
                numsize = "1~50"
            } else if (numsize == "1~50") {
                numsize = "1~25"
            }
            btLv.setText("숫자 크기 : ${numsize}")
        }

        btm.setOnClickListener {
            when (mode) {
                "노멀 모드" -> {
                    mode = "이지 모드"
                }

                "이지 모드" -> {
                    mode = "노멀 모드"
                }
            }
            btm.setText("${mode}")
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