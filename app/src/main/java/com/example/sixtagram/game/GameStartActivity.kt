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
        var mode: String = getString(R.string.game_mode_easy) // "이지" "기본" "하드" "지옥" "Easy" "Base" "Hard" "Hell"

        btSt.setOnClickListener {

            val intent2 = Intent(this, GameMainActivity::class.java)
            intent2.putExtra("numsize", numsize)
            intent2.putExtra("mode", mode)
            startActivity(intent2)
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
        }
        btrk.setOnClickListener {val intent2 = Intent(this, GameEndActivity::class.java)

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
// 0.
// 1. 게임 설명서, 난이도별 설명 페이지 만들기.
// 2. 아이콘 너무왼쪽이니까 오른쪽으로 5dp정도보내기, 텍스트도 좀 오른쪽으로
// 3. 텍스트 위치를 좀더 오른쪾으로
// 4. 뭔가 아이콘 보노보노ppt같은느낌 왼쪽에 하나의 이미지로 1,2,3,4단계 표시하기 ex) 드래곤볼 성구 같은거처럼
// 5. 정보, 설정 글자크기 줄이기 . 우리가 하기로했던 font사이즈중 스몰같은거로
// 6. 아이콘에 눈이 가야지 텍스트에 눈이가면 안좋음 아이콘, 텍스트 다 줄여서 버튼에 눈이가도록
// 7. 게임하러왓는데 게임하기 버튼이 젤 중요하지. 면적 4배정도가지 늘려도 ㄱㅊ
// 8. 중요도에 따라 배치를, 크기도?
// 9. 텍스트는 어떤 게임인지 적고
// 10. 상단바에 게임제목
// 11. 왼쪾 상단바 위에 게임 적기.(다른사람 한거처럼. 커뮤니티 이거처럼)
// 12. ㅂ보라색도 뺴기
// 13. 게임 1to50 이런식으로 상단바에 왼쪽정렬해서
// 14. 게임설명 아이콘은 텍스트의 오른쪾으로 보내고 게임설명 왼쪽으로 보내고
// 15. 보라색 빼고
// 16.