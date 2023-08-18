package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_member.listView1

class MemberActivity : AppCompatActivity() {

    var UserList = arrayListOf<MemUser>(
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms"),
        MemUser(R.drawable.member_profile_msk, name = "kms")
    )

    //    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_member)

        val Adapter = MemUserAdap(this, UserList)
        listView1.adapter = Adapter

        listView1.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as MemUser
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()

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