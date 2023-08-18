package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.calendar.CalendarActivity
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_member.bt_1
import kotlinx.android.synthetic.main.activity_member.bt_2
import kotlinx.android.synthetic.main.activity_member.bt_3
import kotlinx.android.synthetic.main.activity_member.bt_4
import kotlinx.android.synthetic.main.activity_member.et_
import kotlinx.android.synthetic.main.activity_member.listView1
import kotlinx.android.synthetic.main.activity_member.tv_

class MemberActivity : AppCompatActivity() {
    //    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_member)

//        val item = arrayOf("사과", "배", "딸기", "키위", "수박")
//        listView1.adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, item)


        listView1.choiceMode = ListView.CHOICE_MODE_SINGLE
        val items = ArrayList<String>()
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_single_choice,
            items
        )
        listView1.adapter = arrayAdapter
        listView1.setOnItemClickListener { parent, view, position, id ->
            tv_.text = listView1.getItemAtPosition(position) as CharSequence
        }

        bt_1.setOnClickListener() {
            items.add("" + et_.text)
            arrayAdapter.notifyDataSetChanged()//데이터 변경시 알려주는 코드
        }//추가 버튼을 눌렀을때 추가하는 코드

        bt_2.setOnClickListener() {
            val check = listView1.checkedItemPosition
            if (check > -1) {
                items[check] = "" + et_.text
                arrayAdapter.notifyDataSetChanged()
            }
        }

        bt_3.setOnClickListener() {
            val check = listView1.checkedItemPosition
            if (check > -1) {
                items.removeAt(check)
                listView1.clearChoices()
                arrayAdapter.notifyDataSetChanged()
            }//지워진 데이터가 수정이 되지않게 방지
        }

        bt_4.setOnClickListener() {
            items.clear()
            arrayAdapter.notifyDataSetChanged()
        }

//        val currentMember = findViewById<LinearLayout>(R.id.linearLayout_button)
//        currentMember.setOnClickListener {
//            val intent = Intent(this, MemberDetailActivity::class.java)
//            activityResultLauncher.launch(intent)
//
//        }
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