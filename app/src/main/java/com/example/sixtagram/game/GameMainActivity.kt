package com.example.sixtagram.game

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class GameMainActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private val updateIntervalMillis: Long = 10  // 0.01 second
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var elapsedTime = 0L
    private var finalTime: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamemain)

        val buttons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            findViewById<Button>(R.id.button10),
            findViewById<Button>(R.id.button11),
            findViewById<Button>(R.id.button12),
            findViewById<Button>(R.id.button13),
            findViewById<Button>(R.id.button14),
            findViewById<Button>(R.id.button15),
            findViewById<Button>(R.id.button16),
            findViewById<Button>(R.id.button17),
            findViewById<Button>(R.id.button18),
            findViewById<Button>(R.id.button19),
            findViewById<Button>(R.id.button20),
            findViewById<Button>(R.id.button21),
            findViewById<Button>(R.id.button22),
            findViewById<Button>(R.id.button23),
            findViewById<Button>(R.id.button24),
            findViewById<Button>(R.id.button25)
        )

        val originalTextColor = buttons[0].textColors // 원래 텍스트 색상 저장
        val hideTextColor = Color.TRANSPARENT // 텍스트를 숨기기 위해 투명한 색상 지정
        var isTextHidden = false
        val currentTextSizeInPx = buttons[0].textSize // 원래 텍스트 크기
        val btps = findViewById<Button>(R.id.button26)
        val btfn = findViewById<Button>(R.id.button27)
        val btrk = findViewById<Button>(R.id.button28)
        val btri = findViewById<Button>(R.id.button29)
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        chronometer = findViewById(R.id.chronometer)

        var numsize: String? = intent.getStringExtra("numsize") // -1은 시작불가 0은 1~25 1은 1~50
//        var start = 0 // 0은 아직 시작안함 1은 시작
        var gamestate = "정상" // 0은 정상 1은 종료
        var mode: String? = intent.getStringExtra("mode") // 0은 노멀 1은 이지 state3이면 색깔도 변경되도록 하기
        val numbers = (1..25).toList().shuffled()
        val numbers2 = (26..50).toList().shuffled()
        var count = 1
        var countfn = 1
        var count2 = 0 // 이지모드 이전 인덱스 추적용
        var score: Long = 0L
        val originalBackgroundColor2 = buttons[0].backgroundTintList
        val updateColorRunnable = object : Runnable { // 추가해준거1-3
            override fun run() {
                // 모든 버튼의 텍스트 변경
                for (button in buttons) {
                    if (isTextHidden) {
                        button.setTextColor(originalTextColor)
                    } else {
                        button.setTextColor(hideTextColor)
                    }
                }

                isTextHidden = !isTextHidden

                // 1초마다 텍스트 변경 (1000ms = 1s)
                handler.postDelayed(this, 333)
            }
        }

        btps.isEnabled = false
        btfn.isEnabled = false
        chronometer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
            val elapsedMillis = SystemClock.elapsedRealtime() - it.base
            val totalSeconds = elapsedMillis / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            val milliseconds = elapsedMillis % 1000
            it.text = String.format("%02d:%02d.%03d", minutes, seconds, milliseconds)
        }
        tv1.setText("찾아야 되는 숫자 : ${count}")

        btrk.setOnClickListener {
            val intent2 = Intent(this, GameEndActivity::class.java)
//            intent2.putExtra("name", name)
            if ((gamestate == "종료")) {
                score = score(finalTime, numsize, mode, countfn)
                intent2.putExtra("score", score)
                intent2.putExtra("finalTime", finalTime)
                intent2.putExtra("mode", mode)
}
                startActivity(intent2)
        }
        btps.setOnClickListener {
            if ((btps.text == "일시정지") && (gamestate == "정상")) {
                if (count != 1) {
                    for (i in 0..24) {
                        buttons[i].textSize = 0f
                    }
                    handler.removeCallbacks(updateRunnable)
                    btps.text = "일시정지 해제"
                }
            } else if ((btps.text == "일시정지 해제") && (gamestate == "정상")) {
                if (count != 1) {
                    for (i in 0..24) {
                        buttons[i].textSize = 24f
                    }
                    handler.postDelayed(updateRunnable, updateIntervalMillis)
                }
                btps.text = "일시정지"
            } else if (gamestate == "종료") {
                val toast = Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Toast 숨기기
                    toast.cancel()
                }, 500)  // 0.5초 후에 토스트 메시지를 숨깁니다.
            }
        }
        btfn.setOnClickListener {
            var randommessage: String = ""
            var randommessagenumber = 0

            val layoutParams = btfn.layoutParams
            layoutParams.height = dpToPx(this@GameMainActivity, 50f - countfn * 2).toInt()
            btfn.layoutParams = layoutParams
            if ((countfn % 9 == 0)) {
                randommessagenumber = 3

            } else {
                randommessagenumber = (0..2).random()
            }
            if (countfn % 3 == 0) {

                if (countfn == 21) {
                    btfn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                    btfn.setText("버튼이 찌그러져 죽었어요")
                    btfn.setTextColor(Color.BLACK)
                    randommessagenumber = 4
                }
                when (randommessagenumber) {
                    0 -> {
                        randommessage = "${countfn}번이나 힌트를 누르셧군요?"
                    }

                    1 -> {
                        randommessage = "${countfn}번 눌렀으면 힌트없이 해보자구요"
                    }

                    2 -> {
                        randommessage = "힌트를 누르면 최종스코어가 낮아져요."
                    }

                    3 -> {
                        randommessage = "그만!!!!!!! 버튼부서지겠다!!!"
                    }

                    4 -> {
                        randommessage = "버튼을 죽이셨어요"
                    }
                }

                val toast = Toast.makeText(this, "${randommessage}", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Toast 숨기기
                    toast.cancel()
                }, 2000)  // 2초 후에 토스트 메시지를 숨깁니다.
                // 더 이상의 논리식을 실행하지 않습니다.
            }
            if (gamestate == "종료") {
                val toast = Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Toast 숨기기
                    toast.cancel()
                }, 500)  // 0.5초 후에 토스트 메시지를 숨깁니다.
                // 더 이상의 논리식을 실행하지 않습니다.
                return@setOnClickListener
            }

            when (numsize) {
                "1~25" -> {
                    countfn++
                    for (i in 0..24) {
                        if (buttons[i].text == count.toString()) {
                            val originalBackgroundColor = buttons[i].backgroundTintList
                            val originalTextColor = buttons[i].currentTextColor

                            // 클릭 효과 적용
                            buttons[i].backgroundTintList = ColorStateList.valueOf(Color.BLUE)
                            buttons[i].setTextColor(Color.BLACK)

                            // 짧은 시간 후 원래의 색상으로 복구
                            Handler(Looper.getMainLooper()).postDelayed({
                                buttons[i].backgroundTintList = originalBackgroundColor
                                buttons[i].setTextColor(originalTextColor)
                            }, 400)
                            break
                        }
                    }
                }

                "1~50" -> {
                    countfn++
                    for (i in 0..49) {
                        if (buttons[i].text == count.toString()) {
                            val originalBackgroundColor = buttons[i].backgroundTintList
                            val originalTextColor = buttons[i].currentTextColor

                            // 클릭 효과 적용
                            buttons[i].backgroundTintList = ColorStateList.valueOf(Color.BLUE)
                            buttons[i].setTextColor(Color.BLACK)

                            // 짧은 시간 후 원래의 색상으로 복구
                            Handler(Looper.getMainLooper()).postDelayed({
                                buttons[i].backgroundTintList = originalBackgroundColor
                                buttons[i].setTextColor(originalTextColor)
                            }, 400)
                            break
                        }
                    }

                }
            }

        }
        btri.setOnClickListener {
            finish()
            startActivity(intent)
        }

        for (i in buttons.indices) {
            buttons[i].setText("${numbers[i]}")
        }

        if (mode == "이지 모드") {
            for (i in 0..24) {
                if (buttons[i].text == "1") {
                    count2 = i
                    buttons[i].backgroundTintList = ColorStateList.valueOf(Color.BLUE)
                    buttons[i].setTextColor(Color.BLACK)
                }
            }
        }
        for (i in buttons.indices) {
            buttons[i].setOnClickListener {

                if (count.toString() == ((buttons[i].text).toString())) {
                    when (count) {
                        1 -> {
//                                elapsedTime = 0L  // elapsedTime 초기화
                            handler.post(updateRunnable)  // 타이머 시작
                            btps.isEnabled = true
                            btfn.isEnabled = true
                            if (mode == "하드 모드") {
                                handler.post(updateColorRunnable)
                            }
                            if (numsize == "1~25") {
//                                    buttons[i].text = ""
                                buttons[i].visibility = Button.INVISIBLE
                            } else if (numsize == "1~50") {
                                buttons[i].text = numbers2[i].toString()
                            }
                        }

                        in 2..24 -> {
                            if (numsize == "1~50") {
                                buttons[i].text = numbers2[i].toString()
                            } else if (numsize == "1~25") {
//                                    buttons[i].text = ""
                                buttons[i].visibility = Button.INVISIBLE
                            }
                        }

                        25 -> {
                            if (numsize == "1~50") {
                                buttons[i].text = numbers2[i].toString()
                            } else if (numsize == "1~25") {
//                                    buttons[i].text = ""
                                buttons[i].visibility = Button.INVISIBLE
                                handler.removeCallbacks(updateRunnable)  // 타이머 정지
                                finalTime = elapsedTime
                                tv1.setText("끝!!!! 축하드립니다!!!")
                                tv2.setText("기록 : ${score(finalTime, numsize, mode, countfn)}")
                                gamestate = "종료"
                                btps.isEnabled = false
                                btfn.isEnabled = false
                            }
                        }

                        in 26..49 -> {
                            if (numsize == "1~50") {
//                                    buttons[i].text = ""
                                buttons[i].visibility = Button.INVISIBLE
                            }
                        }

                        50 -> {
//                                buttons[i].text = ""
                            buttons[i].visibility = Button.INVISIBLE
                            handler.removeCallbacks(updateRunnable)  // 타이머 정지
                            finalTime = elapsedTime
                            tv1.setText("끝!!!! 축하드립니다!!!")
                            tv2.setText("기록 : ${score(finalTime, numsize, mode, countfn)}")
                            score = score(finalTime, numsize, mode, countfn)
                            gamestate = "종료"
                            btps.isEnabled = false
                            btfn.isEnabled = false

                        }

                    }
                    count++
                    if (mode == "이지 모드") {

                        for (j in 0..24) {
                            if ((count).toString() == ((buttons[j].text).toString())) {
                                buttons[j].backgroundTintList =
                                    ColorStateList.valueOf(Color.BLUE)
                                buttons[j].setTextColor(Color.BLACK)
                                buttons[count2].backgroundTintList = originalBackgroundColor2
                                buttons[count2].setTextColor(Color.WHITE)
                                count2 = j
                                break
                            }
                        }
                    }
                    if (gamestate != "종료") {
                        tv1.setText("찾아야 되는 숫자 : ${count}")
                    }
                }
            }
        }
    }

    fun score(finaltime: Long, state: String?, state3: String?, countfn: Int): Long {
        var score = 0L
        score = 100000000L / finaltime
        when (state3) {
            "노멀 모드" -> {
                score = score * 5 / 2
            }

            "하드 모드" -> {
                score = score * 5
            }
        }
        when (state) {
            "1~50" -> {
                score = score * 7 / 2
            }
        }
        score = score * (100 - 4 * (countfn - 1)) / 100
        return score
    }

    fun dpToPx(context: Context, dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f)
    }

    private val updateRunnable: Runnable = object : Runnable {
        override fun run() {
            elapsedTime += (updateIntervalMillis) * 5 / 3
            val totalSeconds = elapsedTime / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            val milliseconds = elapsedTime % 1000
            chronometer.text = String.format("%02d:%02d.%03d", minutes, seconds, milliseconds)

            handler.postDelayed(this, updateIntervalMillis)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateRunnable)
    }
}

// 추가하고자 하는 기능
// 1. 난이도별 구현하기(1~25 / 1~50 / 1~75) 1~25면 STATE1 1~50이면
// STATE2 이런식으로 STATE 변수 만들면 쉬울거같음. IF에서 STATE 까지 검사하도록 해서.
// 2. 1~25 , 26~50 , X 3가지 경우에 대해 색깔 다르게 하기
// 3. 버튼에 이모티콘같은거 넣기
// 4. 기록을 받아서 랭킹에 저장하기.
// 5. 랭킹에 따라 바로 메인화면에서 랭킹 표시해주기
// 6. 분이 00이면 가려주기. 1 이상일때 띄워주기
// 7. 시간초 0.001초단위 지워보기.
// 8. 버튼들 인접한 버튼이랑 연결하고 패딩과 마진주는식으로 수정하기
// 9. 아래쪽에는 난이도설정, 랭킹, 뒤로가기 등등 ??
// 10. 추가할꺼 : 다음 숫자 찾기 ( 추가함)
// 11. 추가할꺼 : Easy모드(다음 숫자의 버튼이 색깔이 바뀌어서 그 버튼을 누르면 됨
// 11. 추가할꺼 : 근데 Easy모드의 경우 다시 for문돌려서 buttons[i]와 count+1이 같은걸 찾아야할거같은데.
// 12. 추가할꺼 : 난이도 설정 : 이란 TexTvIEW 만들기
// 13. 해야할꺼 : 버튼 모양, 이미지 수정하기
// 14. 추가할꺼 : 완료하면 버튼 전부 없어지면서 폭죽 터지는 .gif같은거 추가?
// 15. 추가할꺼 : 데이터 저장 되면 랭킹 보기
// 16. 추가할꺼 : 게임 일시정지
// 17. 추가할꺼 : 다 쓴 버튼은 사라지게 하기.
// 18. 추가할꺼 : 점수 만들기.
// 18. 추가할꺼 : 이지 , 1~25가 기본
// 18. 추가할꺼 : 노멀 : 2.5배 하드 5배 , 1~50 : 4배
// 18. 추가할꺼 : 다음숫자 찾기 횟수에 따라 점수 차감
// 19. 클릭했을때 select
// 20. 1 클릭했을떄 시작을 알려주는 뭔가를 추가해주기
// 21. 버튼을 누르면 잠깐 이미지 바뀌고 사라지게끔 select 이용
// 22. 다음 화면 3 2 1 하고 화면이 뜨게끔.
// 23. 랜덤으로 뜨게해보자 토글메세지.
// 24. 버튼 어느한도 이상으로 누르면 버튼 부서지는 모양도 추가해보자.
// 25. 그리고 버튼 부서지겟다는 어느한도이상일때만 나오도록(0~2까지만 구현하고 count
// 26. 하드모드(숫자가 잠시동안 나타낫다 사라졋다를 반복하게끔)
// 26. 지옥모드(숫자가 잠시동안 나타낫다 사라졋다를 반복하게끔 + 5번 깜빡할때마다 숫자가 바뀜)
// 27. 점수 만들기
// 28. 게임 일시정지(일시 정지때는 숫자 전부 가리기)
// 29. 게임 설명서?
// 30. 플레이스토어에 1to50 비슷한 게임들 찾아보면서 비교해가면서 추가할만한거 찾아보기
// 31. 점수 : 100,000,0 / 흐른 밀리초
// 31. 점수 : 노멀은 2.5배, 하드 5배 1~5은 3.5배 곱하기
// 31. score = ( 100,000,000 / finalTime )
// 31. when(state3) { "노멀 모드" -> {score = score * 2.5 "하드 모드" -> {score = score * 5}}
// 31. when(state) {"1~50" -> {score = score * 3.5 }}
// 31. countfn 힌트 찾으면 점수 깎이는 로직. (1 - 0.04 * (countfn-1))
// 32. F2를 누르고 alt + enter 한다음 yellow메세지에 대해 수정하고 공부하면 좀 더 좋다.
// 33. dp를 전부 sp로 수정하기(.xml)