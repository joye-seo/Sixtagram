package com.example.sixtagram.game

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        val btps = findViewById<Button>(R.id.button26)
        val btfn = findViewById<Button>(R.id.button27)
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val tv3 = findViewById<TextView>(R.id.textView3)
        val tv5 = findViewById<TextView>(R.id.textView5)
        val tv6 = findViewById<TextView>(R.id.textView6)
        val tv7 = findViewById<TextView>(R.id.textView7)
        val tv8 = findViewById<TextView>(R.id.textView8)
        val iv7 = findViewById<ImageView>(R.id.imageView7)
        val iv8 = findViewById<ImageView>(R.id.imageView8)
        val iv9 = findViewById<ImageView>(R.id.imageView9)
        val iv10 = findViewById<ImageView>(R.id.imageView10)
        val iv11 = findViewById<ImageView>(R.id.imageView11)
        chronometer = findViewById(R.id.chronometer)
        var numbers = (1..25).toList().shuffled() // 버튼 숫자 랜덤배치
        var numbers2 = (26..50).toList().shuffled() // 버튼 숫자 랜덤배치
        var numsize: String? = intent.getStringExtra("numsize") // "25" "50"
        var mode: String? = intent.getStringExtra("mode") // "이지 모드" "노멀 모드" 하드 모드" "지옥 모드"
        var gamestate = getString(R.string.game_gameStateRunning) // "정상" "종료"

        val originalBackgroundColor =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.pantone_189))
        val originalTextColor = buttons[0].textColors // 원래 텍스트 색상 저장
        val hintcolor = ContextCompat.getColor(this, R.color.pantone_red) // 힌트 받은 색깔
        val hideTextColor = Color.TRANSPARENT // 텍스트를 숨기기 위해 투명한 색상 지정
        var isTextHidden = false // 텍스트 숨기기 위한 논리 변수

        var findnumber = 1 // 다음숫자
        var countfn = 1 // 힌트버튼 카운트
        var countfn2 = 1 // 일시정지 카운트
        var count2 = 0 // 이지 모드 이전 인덱스 추적용
        var count3 = 0 // 지옥 모드 카운트
        var count4 = 25 // 남은 버튼 확인용
        var count5 = 0 // 헬모드 인덱스 추적용
        var score: Long = 0L
        var randommessage: String = ""
        var randommessagenumber = 0
        tv8.setText("${getString(R.string.game_game_difficulty)} ${mode}")
        val updateColorRunnable = object : Runnable {
            override fun run() {
                if (mode == getString(R.string.game_mode_hard) || (mode == getString(R.string.game_mode_hell))) {
                    // 모든 버튼의 텍스트 변경
                    for (i in 0..24) {
                        if (isTextHidden) {
                            buttons[i].setTextColor(originalTextColor)
                            if (mode == getString(R.string.game_mode_hell)) {

                                buttons[i].isClickable = true
                            }
                        } else {
                            buttons[i].setTextColor(hideTextColor)
                            if (mode == getString(R.string.game_mode_hell)) {

                                buttons[i].isClickable = false
                            }
                        }
                    }
                }
                count3++
                if ((count3 == 6) && (mode == getString(R.string.game_mode_hell))) {
                    numbers = (findnumber..findnumber + count4 - 1).toList().shuffled()
                    count3 = 0
                    for (i in 0..24) {
                        if (buttons[i].visibility != Button.INVISIBLE) {
                            buttons[i].setText("${numbers[count4 - 1 - count5]}")
                            count5++
                        }
                    }
                    count5 = 0
                }
                isTextHidden = !isTextHidden

                handler.postDelayed(this, 400)
            }
        }
        val ScoreupdateRunnable: Runnable = object : Runnable {
            override fun run() {
                tv2.setText(
                    "${getString(R.string.game_gamescore)}${
                        score(
                            elapsedTime,
                            numsize,
                            mode,
                            countfn
                        )
                    }"
                )
                handler.postDelayed(this, 10)
            }
        }
        chronometer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
            val elapsedMillis = SystemClock.elapsedRealtime() - it.base
            val totalSeconds = elapsedMillis / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            val milliseconds = elapsedMillis % 1000
            it.text = String.format("%02d:%02d.%03d", minutes, seconds, milliseconds)
        }
        tv1.setText("${getString(R.string.game_targetNumber)} ${findnumber}")

        btps.isEnabled = false
        btfn.isEnabled = false

        btps.setOnClickListener {
            if ((btps.text == getString(R.string.game_gameStatePlayed)) && (gamestate == (getString(
                    R.string.game_gameStateRunning
                )))
            ) {
                if (findnumber != 1) {
                    for (i in 0..24) {
                        buttons[i].textSize = 0f
                        buttons[i].isClickable = false
                    }
                    handler.removeCallbacks(updateRunnable)
                    btps.text = getString(R.string.game_gameStatePaused)
                }
            } else if ((btps.text == getString(R.string.game_gameStatePaused)) && (gamestate == (getString(
                    R.string.game_gameStateRunning
                )))
            ) {
                if (findnumber != 1) {
                    for (i in 0..24) {
                        buttons[i].textSize = 20f
                        buttons[i].isClickable = true
                    }
                    handler.postDelayed(updateRunnable, updateIntervalMillis)
                }
                btps.text = getString(R.string.game_gameStatePlayed)
            }
            val layoutParams = btps.layoutParams
            layoutParams.height = dpToPx(this@GameMainActivity, 50f - countfn2 * 2).toInt()
            btps.layoutParams = layoutParams
            val layoutParams2 = iv7.layoutParams
            layoutParams2.height = dpToPx(this@GameMainActivity, 50f - countfn2 * 2).toInt()
            iv7.layoutParams = layoutParams2
            val layoutParams3 = iv8.layoutParams
            layoutParams3.height = dpToPx(this@GameMainActivity, 50f - countfn2 * 2).toInt()
            iv8.layoutParams = layoutParams3
            if (iv7.visibility == View.VISIBLE) {
                iv7.visibility = View.INVISIBLE
                iv8.visibility = View.VISIBLE
            } else {
                iv8.visibility = View.INVISIBLE
                iv7.visibility = View.VISIBLE
            }
            if ((countfn2 % 9 == 0)) {
                randommessagenumber = 3
            } else {
                randommessagenumber = (0..2).random()
            }
            if ((countfn2 % 3 == 0) || (countfn2 % 20 == 0)) {
                if (countfn2 == 20) {
                    btps.visibility = View.INVISIBLE
                    iv7.visibility = View.INVISIBLE
                    iv8.visibility = View.INVISIBLE
                    tv5.visibility = View.VISIBLE
                    randommessagenumber = 4
                    if (btfn.visibility == View.INVISIBLE) {
                        iv11.visibility = View.INVISIBLE
                        tv6.visibility = View.INVISIBLE
                    }
                }
                when (randommessagenumber) {
                    0 -> {
                        randommessage = getString(R.string.game_toggle_message2_0, countfn2)
                    }

                    1 -> {
                        randommessage = getString(R.string.game_toggle_message2_1, countfn2)
                    }

                    2 -> {
                        randommessage = getString(R.string.game_toggle_message2_2)
                    }

                    3 -> {
                        randommessage = getString(R.string.game_toggle_message2_3)
                    }

                    4 -> {
                        randommessage = getString(R.string.game_toggle_message2_4)
                    }
                }
                val toast = Toast.makeText(this, "${randommessage}", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Toast 숨기기
                    toast.cancel()
                }, 2000)  // (delayMillis/2000)초 후에 토스트 메시지를 숨깁니다.
            }
            countfn2++
        }
        btfn.setOnClickListener {
            if (mode == getString(R.string.game_mode_easy)) {
                val toast = Toast.makeText(
                    this,
                    getString(R.string.game_toggle_message_easy),
                    Toast.LENGTH_SHORT
                )
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Toast 숨기기
                    toast.cancel()
                }, 2000)  // (delayMillis/2000)초 후에 토스트 메시지를 숨깁니다.
                return@setOnClickListener
            }
            val layoutParams = btfn.layoutParams
            layoutParams.height = dpToPx(this@GameMainActivity, 50f - countfn * 2).toInt()
            btfn.layoutParams = layoutParams
            val layoutParams2 = iv9.layoutParams
            layoutParams2.height = dpToPx(this@GameMainActivity, 50f - countfn * 2).toInt()
            iv9.layoutParams = layoutParams2
            if ((countfn % 9 == 0)) {
                randommessagenumber = 3
            } else {
                randommessagenumber = (0..2).random()
            }
            if (countfn % 3 == 0) {
                if (countfn == 21) {
                    btfn.visibility = View.INVISIBLE
                    iv9.visibility = View.INVISIBLE
                    tv3.visibility = View.VISIBLE
                    randommessagenumber = 4
                    if (btps.visibility == View.INVISIBLE) {
                        iv11.visibility = View.INVISIBLE
                        tv6.visibility = View.INVISIBLE
                    }
                }
                when (randommessagenumber) {
                    0 -> {
                        randommessage = getString(R.string.game_toggle_message_0, countfn)
                    }

                    1 -> {
                        randommessage = getString(R.string.game_toggle_message_1, countfn)
                    }

                    2 -> {
                        randommessage = getString(R.string.game_toggle_message_2)
                    }

                    3 -> {
                        randommessage = getString(R.string.game_toggle_message_3)
                    }

                    4 -> {
                        randommessage = getString(R.string.game_toggle_message_4)
                    }
                }
                val toast = Toast.makeText(this, "${randommessage}", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Toast 숨기기
                    toast.cancel()
                }, 2000)  // (delayMillis/2000)초 후에 토스트 메시지를 숨깁니다.
            }
            countfn++
            for (i in 0..49) {
                if (buttons[i].text == findnumber.toString()) {
                    // 클릭 효과 적용
                    buttons[i].backgroundTintList = ColorStateList.valueOf(hintcolor)

                    // 짧은 시간 후 원래의 색상으로 복구
                    Handler(Looper.getMainLooper()).postDelayed({
                        buttons[i].backgroundTintList = originalBackgroundColor
                    }, 800)
                    break
                }
            }

        }
        iv10.setOnClickListener {
            finish()
            startActivity(intent)
        }

        for (i in buttons.indices) {
            buttons[i].setText("${numbers[i]}")
        }

        if (mode == getString(R.string.game_mode_easy)) {
            for (i in 0..24) {
                if (buttons[i].text == "1") {
                    count2 = i
                    buttons[i].backgroundTintList = ColorStateList.valueOf(hintcolor)
                }
            }
        }
        for (i in buttons.indices) {
            buttons[i].setOnClickListener {

                if (findnumber.toString() == ((buttons[i].text).toString())) {
                    when (findnumber) {
                        1 -> {
                            handler.post(updateRunnable)  // 타이머 시작
                            handler.post(ScoreupdateRunnable)
                            btps.isEnabled = true
                            btfn.isEnabled = true
                            iv7.visibility = View.VISIBLE
                            btps.setText(getString(R.string.game_gameStatePlayed))
                            tv7.visibility = View.INVISIBLE
                            if ((mode == getString(R.string.game_mode_hard)) || (mode == getString(R.string.game_mode_hell))) {
                                handler.post(updateColorRunnable)
                            }
                            if (numsize == getString(R.string.game_numsize_25)) {
                                buttons[i].visibility = Button.INVISIBLE
                                count4--
                            } else if (numsize == getString(R.string.game_numsize_50)) {
                                buttons[i].text = numbers2[i].toString()
                            }
                        }

                        in 2..24 -> {
                            if (numsize == getString(R.string.game_numsize_50)) {
                                buttons[i].text = numbers2[i].toString()

                            } else if (numsize == getString(R.string.game_numsize_25)) {
                                buttons[i].visibility = Button.INVISIBLE
                                count4--
                            }
                        }

                        25 -> {
                            if (numsize == getString(R.string.game_numsize_50)) {
                                buttons[i].text = numbers2[i].toString()
                            } else if (numsize == getString(R.string.game_numsize_25)) {
                                gamestate = getString(R.string.game_gameStateFinished)
                            }
                        }

                        in 26..49 -> {
                            if (numsize == getString(R.string.game_numsize_50)) {
                                buttons[i].visibility = Button.INVISIBLE
                                count4--
                            }
                        }

                        50 -> {
                            gamestate = getString(R.string.game_gameStateFinished)
                        }
                    }
                    findnumber++
                    if (mode == getString(R.string.game_mode_easy)) {
                        for (j in 0..24) {
                            if ((findnumber).toString() == ((buttons[j].text).toString())) {
                                buttons[j].backgroundTintList = ColorStateList.valueOf(hintcolor)
                                buttons[count2].backgroundTintList = originalBackgroundColor
                                count2 = j
                                break
                            }
                        }
                    }
                    if (gamestate != getString(R.string.game_gameStateFinished)) {
                        tv1.setText("${getString(R.string.game_targetNumber)}${findnumber}")
                    } else if (gamestate == getString(R.string.game_gameStateFinished)) {
                        buttons[i].visibility = Button.INVISIBLE
                        count4--
                        handler.removeCallbacks(updateRunnable)  // 타이머 정지
                        handler.removeCallbacks(ScoreupdateRunnable)
                        finalTime = elapsedTime
                        tv1.setText(getString(R.string.game_message_ending))
                        tv2.setText(
                            "${getString(R.string.game_gamescore)}${
                                score(
                                    finalTime,
                                    numsize,
                                    mode,
                                    countfn
                                )
                            }"
                        )
                        score = score(finalTime, numsize, mode, countfn)
                        btps.visibility = View.INVISIBLE
                        btfn.visibility = View.INVISIBLE
                        tv6.visibility = View.INVISIBLE
                        iv7.visibility = View.INVISIBLE
                        iv8.visibility = View.INVISIBLE
                        iv9.visibility = View.INVISIBLE
                        iv11.visibility = View.INVISIBLE
                        val intent2 = Intent(this, GameEndActivity::class.java)
                        score = score(finalTime, numsize, mode, countfn)
                        intent2.putExtra("score", score)
                        intent2.putExtra("finalTime", finalTime)
                        intent2.putExtra("mode", mode)
                        intent2.putExtra("numsize", numsize)
                        startActivity(intent2)
                    }
                }
            }
        }
    }

    fun score(time: Long, state: String?, state3: String?, countfn: Int): Long {
        var score = 0L
        if (time >= 100) {
            score = 100000000L / time
        } else {
            score = 100000000L
        }
        when (state3) {
            getString(R.string.game_mode_base) -> {
                score = score * 5 / 2
            }

            getString(R.string.game_mode_hard) -> {
                score = score * 5
            }

            getString(R.string.game_mode_hell) -> {
                score = score * 15
            }
        }
        when (state) {
            getString(R.string.game_numsize_50) -> {
                score = score * 4
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
            val milliseconds = (elapsedTime % 1000) / 10
            chronometer.text = String.format("%02d:%02d.%02d", minutes, seconds, milliseconds)

            handler.postDelayed(this, updateIntervalMillis)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}

