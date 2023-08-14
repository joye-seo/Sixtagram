package com.example.sixtagram.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sixtagram.R
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
class GameActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private val updateIntervalMillis: Long = 10  // 0.01 second
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var elapsedTime = 0L
    private var finalTime: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
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
        val btLv = findViewById<Button>(R.id.button26)
        val btfn = findViewById<Button>(R.id.button27)
        var state = -1 // -1은 시작불가 0은 1~25 1은 1~50
        var start = 0 // 0은 아직 시작안함 1은 시작
        var state2 = 0 // 0은 정상 1은 종료
        val numbers = (1..25).toList().shuffled()
        val numbers2 = (26..50).toList().shuffled()
        var count = 1
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        chronometer = findViewById(R.id.chronometer)
        chronometer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
            val elapsedMillis = SystemClock.elapsedRealtime() - it.base
            val totalSeconds = elapsedMillis / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            val milliseconds = elapsedMillis % 1000
            it.text = String.format("%02d:%02d.%03d", minutes, seconds, milliseconds)
        }
        tv1.setText("찾아야 되는 숫자 : ${count}")

        btLv.setOnClickListener {
            if (((state == 1) && (start == 0)) || (state == -1)) {
                state = 0
                btLv.setText("1~25")
            } else if (((state == 0) && (start == 0)) || (state == -1)) {
                state = 1
                btLv.setText("1~50")
            } else {
                if(state2 == 1){
                    Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "게임이 진행중입니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }
        btfn.setOnClickListener {
            if(state2 == 1)
            {
                Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT).show()
                // 더 이상의 논리식을 실행하지 않습니다.
                return@setOnClickListener
            }
            if (start == 0) {
                Toast.makeText(this, "게임을 시작해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                when (state) {
                    -1 -> {
                        Toast.makeText(this, "난이도를 설정해 주세요.", Toast.LENGTH_SHORT).show()
                    }

                    0 -> {
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

                    1 -> {
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
        }

        for (i in buttons.indices) {
            buttons[i].setText("${numbers[i]}")
        }
        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                if (state == -1) {
                    Toast.makeText(this, "난이도를 설정해 주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    if (count.toString() == ((buttons[i].text).toString())) {
                        when (count) {
                            1 -> {
                                start = 1
                                elapsedTime = 0L  // elapsedTime 초기화
                                handler.post(updateRunnable)  // 타이머 시작
                                if (state == 0) {
                                    buttons[i].text = ""
                                } else if (state == 1) {
                                    buttons[i].text = numbers2[i].toString()

                                }
                            }

                            in 2..24 -> {
                                if (state == 1) {
                                    buttons[i].text = numbers2[i].toString()
                                } else if (state == 0) {
                                    buttons[i].text = ""
                                }
                            }

                            25 -> {
                                if (state == 1) {
                                    buttons[i].text = numbers2[i].toString()
                                } else if (state == 0) {
                                    buttons[i].text = ""
                                    handler.removeCallbacks(updateRunnable)  // 타이머 정지
                                    finalTime = elapsedTime
                                    tv1.setText("끝!!!! 축하드립니다!!!")
                                    tv2.setText("기록 : ${finalTime / 1000}.${finalTime % 1000}")
                                    state2 = 1
                                }
                            }

                            in 26..49 -> {
                                if (state == 1) {
                                    buttons[i].text = ""
                                }
                            }

                            50 -> {
                                buttons[i].text = ""
                                handler.removeCallbacks(updateRunnable)  // 타이머 정지
                                finalTime = elapsedTime
                                tv1.setText("끝!!!! 축하드립니다!!!")
                                tv2.setText("기록 : ${finalTime / 1000}.${finalTime % 1000}")
                                state2 = 1
                            }

                        }
                        count++
                        if (state2 != 1) {
                            tv1.setText("찾아야 되는 숫자 : ${count}")
                        }
                    }
                } // 큰 if
            }
        }
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
// 10. 추가할꺼 : 다음 숫자 찾기
