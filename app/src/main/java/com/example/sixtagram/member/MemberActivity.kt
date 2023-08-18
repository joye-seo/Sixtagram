package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MemberActivity : AppCompatActivity() {


    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        val listView1 = findViewById<ListView>(R.id.listView1)
        val Adapter = MemUserAdap(this, UserList)

        listView1.adapter = Adapter

        listView1.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

//            val intent = Intent(this, MemberDetailActivity::class.java)
//            intent.putExtra("Member", UserList[position])
//            startActivity(intent)

            }
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

    var UserList = arrayListOf<MemUser>(
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_dkl, name = "ldk"),
        MemUser(R.drawable.member_profile_sjl, name = "lsj"),
        MemUser(R.drawable.member_profile_shs, name = "ssh"),
        MemUser(R.drawable.member_profile_wjc, name = "cwj"),
        MemUser(R.drawable.ic_heart, name = "cwj"),
        MemUser(R.drawable.member_profile_wjc, name = "heart"),
        MemUser(R.drawable.ic_member_test, name = "asd")
    )

    //        lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        setContentView(R.layout.activity_member)

}

