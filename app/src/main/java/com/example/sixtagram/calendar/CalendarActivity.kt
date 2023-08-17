package com.example.sixtagram.calendar

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sixtagram.R
import com.example.sixtagram.community.CommunityActivity
import com.example.sixtagram.game.GameStartActivity
import com.example.sixtagram.member.MemberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarActivity : AppCompatActivity() {

    val REQUEST_FOR_NOTION = 1005

    private val bottomNav: BottomNavigationView by lazy {
        findViewById(R.id.bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calendar)

        initNavigation()

        val dayText : TextView = findViewById(R.id.dayText)
        val calendarView : CalendarView = findViewById(R.id.calendarView)
        val dateFormat : DateFormat = SimpleDateFormat("yyyy년 MM월 dd일 EEEE", Locale.getDefault())
        val date : Date = Date(calendarView.date)







        dayText.text = dateFormat.format(date)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year , month , dayOfMonth )

            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val selectedDate = Date(calendar.timeInMillis)

            dayText.text = dateFormat.format(selectedDate)

            when (dayOfWeek) {
                Calendar.SATURDAY -> dayText.setTextColor(Color.parseColor("#0000FF"))
                Calendar.SUNDAY -> dayText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                else -> dayText.setTextColor(ContextCompat.getColor(this, android.R.color.black))
            }

        }   //캘린더의 날짜가 변경될때마다 실행되는 리스너









        val homeBtn : TextView = findViewById(R.id.homeBtn)
        homeBtn.setOnClickListener {

            val calendar = Calendar.getInstance()
            val todayMillis = calendar.timeInMillis
            calendarView.date = todayMillis

            val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
            dayText.text = String.format("%04d년 %02d월 %02d일 %s요일",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                dayOfWeek)




        } // 홈 버튼 누를시 오늘 날짜로 돌아옴!







        val writebtn: FloatingActionButton = findViewById(R.id.writeBtn)  //메모장 글쓰기 버튼
        writebtn.setOnClickListener {
            val myIntent = Intent(this,MemoActivity::class.java)
            startActivity(myIntent)

        }







       val notionbtn : ImageView = findViewById(R.id.notionBtn)  //공지사항 수정시 버튼
        notionbtn.setOnClickListener{
            val myIntent = Intent(this , NotionTextEditActivity::class.java)
            startActivityForResult(myIntent , REQUEST_FOR_NOTION)
        }
    }






    private fun saveMemo(memo : String) {

        val sharedPreferences = getSharedPreferences("MyPreferences" , MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("memo",memo)
        editor.apply()
    }       //메모 저장
    private fun getMemo() : String? {
        val  sharedPreferences = getSharedPreferences("MyPreferences" , MODE_PRIVATE)
        return  sharedPreferences.getString("memo","")
    }        //메모 저장









    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FOR_NOTION){
            if (resultCode == Activity.RESULT_OK){
                val newNotionTxt =data?.getStringExtra("notion")
                val notionTxt : TextView= findViewById(R.id.notionTxt)
                notionTxt.text = newNotionTxt
            }
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
