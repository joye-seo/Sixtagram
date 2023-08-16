package com.example.sixtagram.calendar

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarActivity : AppCompatActivity() {

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calendar)

        initNavigation()

        val dayText : TextView = findViewById(R.id.dayText)                  //객체 생성
        val calendarView : CalendarView = findViewById(R.id.calendarView)    //객체 생성

        val dateFormat : DateFormat = SimpleDateFormat("yyyy년 MM월 dd일 EEEE", Locale.getDefault())//날짜 형태



        val date : Date = Date(calendarView.date)      //오늘 날짜

        dayText.text = dateFormat.format(date) //날짜 텍스트뷰에 담기

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth -> //날짜 변환 이벤트

            val calendar = Calendar.getInstance()

            calendar.set(year , month , dayOfMonth )

            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            val selectedDate = Date(calendar.timeInMillis)

            dayText.text = dateFormat.format(selectedDate)


            if (dayOfWeek == Calendar.SATURDAY){

                dayText.setTextColor((resources.getColor(android.R.color.holo_blue_dark)))

            }  else if (dayOfWeek == Calendar.SUNDAY) {

                dayText.setTextColor((resources.getColor(android.R.color.holo_red_dark)))
            }
            else{
                dayText.setTextColor(resources.getColor(android.R.color.black))

            }
        }




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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }
}