package com.example.sixtagram.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
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
        val btrk = findViewById<Button>(R.id.button5)
        val iv17 = findViewById<ImageView>(R.id.imageView17)
        val iv18 = findViewById<ImageView>(R.id.imageView18)
        val iv19 = findViewById<ImageView>(R.id.imageView19)
        val iv20 = findViewById<ImageView>(R.id.imageView20)
        var numsize: String = getString(R.string.game_numsize_25) // "1~25" "1~50"
        var mode: String =
            getString(R.string.game_mode_easy) // "이지" "기본" "하드" "지옥" "Easy" "Base" "Hard" "Hell"

        btSt.setOnClickListener {

            val intent2 = Intent(this, GameMainActivity::class.java)
            intent2.putExtra("numsize", numsize)
            intent2.putExtra("mode", mode)
            startActivity(intent2)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btLv.setOnClickListener {

            if (numsize == getString(R.string.game_numsize_25)) {
                numsize = getString(R.string.game_numsize_50)
                iv20.visibility = View.VISIBLE
            } else if (numsize == getString(R.string.game_numsize_50)) {
                numsize = getString(R.string.game_numsize_25)
                iv20.visibility = View.INVISIBLE
            }
            btLv.setText("${getString(R.string.game_numsize)}${numsize}")
        }

        btm.setOnClickListener {

            when (mode) {
                getString(R.string.game_mode_easy) -> {
                    mode = getString(R.string.game_mode_base)
                    iv17.visibility = View.VISIBLE
                }

                getString(R.string.game_mode_base) -> {
                    mode = getString(R.string.game_mode_hard)
                    iv18.visibility = View.VISIBLE
                }

                getString(R.string.game_mode_hard) -> {
                    mode = getString(R.string.game_mode_hell)
                    iv19.visibility = View.VISIBLE
                }

                getString(R.string.game_mode_hell) -> {
                    mode = getString(R.string.game_mode_easy)
                    iv17.visibility = View.INVISIBLE
                    iv18.visibility = View.INVISIBLE
                    iv19.visibility = View.INVISIBLE
                }
            }
            btm.setText("${getString(R.string.game_game_difficulty)} ${mode} ${getString(R.string.game_mode)}")
        }
        btd.setOnClickListener {
            val intent2 = Intent(this, GameDescriptionActivity::class.java)
            startActivity(intent2)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        btrk.setOnClickListener {
            val intent2 = Intent(this, GameEndActivity::class.java)
            startActivity(intent2)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
