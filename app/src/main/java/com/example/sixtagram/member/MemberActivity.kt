package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Layout
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MemberActivity : AppCompatActivity() {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_member)

        val currentMember = findViewById<LinearLayout>(R.id.linearLayout_button)
        currentMember.setOnClickListener {
            val intent = Intent(this, MemberDetailActivity::class.java)
            activityResultLauncher.launch(intent)

        }
    }




    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
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
}