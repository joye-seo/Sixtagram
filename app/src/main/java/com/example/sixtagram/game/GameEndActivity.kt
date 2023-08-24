package com.example.sixtagram.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sixtagram.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Calendar

class GameEndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameend)
        val score = intent.getLongExtra("score", 0L)
        val finalTime = intent.getLongExtra("finalTime", 0L)
        val mode = intent.getStringExtra("mode")
        val numsize = intent.getStringExtra("numsize")
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1  // MONTH는 0부터 시작하므로 1을 더해줍니다.
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val tv3 = findViewById<TextView>(R.id.textView3)
        val btdd = findViewById<Button>(R.id.button1)
        val btrti = findViewById<Button>(R.id.button2)

        if (score != 0L) { // 시작 화면에서 랭킹보기로 오면 0점이 추가되서 0이 아닐때만 데이터가 추가되도록
            tv3.setText(
                "${getString(R.string.game_end_current)}  ${
                    String.format(
                        "%06d",
                        score
                    )
                }${getString(R.string.game_ResetRankings_score)} | ${
                    String.format(
                        "%03d",
                        finalTime / 1000
                    )
                }.${
                    String.format(
                        "%02d",
                        (finalTime % 1000) / 10
                    )
                }${getString(R.string.game_ResetRankings_second)} | ${mode} | ${numsize}"
            )
            saveGameData(score, finalTime, mode, numsize, month, day, hour, minute)
        }
        displayGameData()
        btdd.setOnClickListener {
            val toast = Toast.makeText(
                this,
                getString(R.string.game_ResetRankings_toggle_message),
                Toast.LENGTH_SHORT
            )
            toast.show()
            Handler(Looper.getMainLooper()).postDelayed({
                // Toast 숨기기
                toast.cancel()
            }, 2000)  // (delayMillis/2000)초 후에 토스트 메시지를 숨깁니다.
            val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            displayGameData() // 화면 갱신
        }
        btrti.setOnClickListener {
            val intent2 = Intent(this, GameStartActivity::class.java)
            finish()
            startActivity(intent2)
        }
    }


    private fun saveGameData(
        score: Long,
        finalTime: Long,
        mode: String?,
        numsize: String?,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int
    ) {
        val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // 기존의 score 데이터 리스트를 가져옵니다.
        val existingScores = sharedPref.getString("scores", "[]")
        val typeTokenScore = object : TypeToken<List<Long>>() {}.type
        val scoreList = Gson().fromJson<List<Long>>(existingScores, typeTokenScore).toMutableList()

        // 기존의 finalTime 데이터 리스트를 가져옵니다.
        val existingFinalTimes = sharedPref.getString("finalTimes", "[]")
        val typeTokenFinalTime = object : TypeToken<List<Long>>() {}.type
        val finalTimeList =
            Gson().fromJson<List<Long>>(existingFinalTimes, typeTokenFinalTime).toMutableList()

        // 기존의 mode 데이터 리스트를 가져옵니다.
        val existingmodes = sharedPref.getString("modes", "[]")
        val typeTokenmode = object : TypeToken<List<String?>>() {}.type
        val modeList = Gson().fromJson<List<String?>>(existingmodes, typeTokenmode).toMutableList()

        // 기존의 numsizes 데이터 리스트를 가져옵니다.
        val existingnumsizes = sharedPref.getString("numsizes", "[]")
        val typeTokennumsize = object : TypeToken<List<String?>>() {}.type
        val numsizeList =
            Gson().fromJson<List<String?>>(existingnumsizes, typeTokennumsize).toMutableList()

        // 기존의 month 데이터 리스트를 가져옵니다.
        val existingmonths = sharedPref.getString("months", "[]")
        val typeTokenmonth = object : TypeToken<List<Int>>() {}.type
        val monthList = Gson().fromJson<List<Int>>(existingmonths, typeTokenmonth).toMutableList()

        // 기존의 day 데이터 리스트를 가져옵니다.
        val existingdays = sharedPref.getString("days", "[]")
        val typeTokenday = object : TypeToken<List<Int>>() {}.type
        val dayList = Gson().fromJson<List<Int>>(existingdays, typeTokenday).toMutableList()

        // 기존의 hour 데이터 리스트를 가져옵니다.
        val existinghours = sharedPref.getString("hours", "[]")
        val typeTokenhour = object : TypeToken<List<Int>>() {}.type
        val hourList = Gson().fromJson<List<Int>>(existinghours, typeTokenhour).toMutableList()

        // 기존의 minute 데이터 리스트를 가져옵니다.
        val existingminutes = sharedPref.getString("minutes", "[]")
        val typeTokenminute = object : TypeToken<List<Int>>() {}.type
        val minuteList =
            Gson().fromJson<List<Int>>(existingminutes, typeTokenminute).toMutableList()

        // 새로운 데이터를 리스트에 추가합니다.

        scoreList.add(score)
        finalTimeList.add(finalTime)
        modeList.add(mode)
        numsizeList.add(numsize)
        monthList.add(month)
        dayList.add(day)
        hourList.add(hour)
        minuteList.add(minute)

        // 리스트를 다시 JSON 문자열로 변환하여 저장합니다.
        editor.putString("scores", Gson().toJson(scoreList))
        editor.putString("finalTimes", Gson().toJson(finalTimeList))
        editor.putString("modes", Gson().toJson(modeList))
        editor.putString("numsizes", Gson().toJson(numsizeList))
        editor.putString("months", Gson().toJson(monthList))
        editor.putString("days", Gson().toJson(dayList))
        editor.putString("hours", Gson().toJson(hourList))
        editor.putString("minutes", Gson().toJson(minuteList))
        editor.apply()
    }

    private fun displayGameData() {
        val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)

        val existingScores = sharedPref.getString("scores", "[]")
        val scoreList =
            Gson().fromJson<List<Long>>(existingScores, object : TypeToken<List<Long>>() {}.type)

        val existingFinalTimes = sharedPref.getString("finalTimes", "[]")
        val finalTimeList = Gson().fromJson<List<Long>>(
            existingFinalTimes,
            object : TypeToken<List<Long>>() {}.type
        )

        val existingmodes = sharedPref.getString("modes", "[]")
        val modeList = Gson().fromJson<List<String?>>(
            existingmodes,
            object : TypeToken<List<String?>>() {}.type
        )

        val existingnumsizes = sharedPref.getString("numsizes", "[]")
        val numsizeList = Gson().fromJson<List<String?>>(
            existingnumsizes,
            object : TypeToken<List<String?>>() {}.type
        )
        val existingmonths = sharedPref.getString("months", "[]")
        val monthList = Gson().fromJson<List<Int>>(
            existingmonths,
            object : TypeToken<List<Int>>() {}.type
        )
        val existingdays = sharedPref.getString("days", "[]")
        val dayList = Gson().fromJson<List<Int>>(
            existingdays,
            object : TypeToken<List<Int>>() {}.type
        )
        val existinghours = sharedPref.getString("hours", "[]")
        val hourList = Gson().fromJson<List<Int>>(
            existinghours,
            object : TypeToken<List<Int>>() {}.type
        )
        val existingminutes = sharedPref.getString("minutes", "[]")
        val minuteList = Gson().fromJson<List<Int>>(
            existingminutes,
            object : TypeToken<List<Int>>() {}.type
        )
        val recordsLayout = findViewById<LinearLayout>(R.id.recordsLayout)
        recordsLayout.removeAllViews() // 기존에 추가된 뷰 제거

        val scoreListSize = scoreList.size
        val scoreList2 =
            MutableList<Long>(scoreListSize) { 0L } // 원본 데이터는 정렬하는 방법을 몰라서 동일한 배열을 만들고 그걸 내림차순 정렬
        val finalTimeList2 = MutableList<Long>(scoreListSize) { 0L }
        val modeList2 = MutableList<String?>(scoreListSize) { "" }
        val numsizeList2 = MutableList<String?>(scoreListSize) { "" }
        val monthList2 = MutableList<Int>(scoreListSize) { 0 }
        val dayList2 = MutableList<Int>(scoreListSize) { 0 }
        val hourList2 = MutableList<Int>(scoreListSize) { 0 }
        val minuteList2 = MutableList<Int>(scoreListSize) { 0 }
//
        val sortedIndices = scoreList.indices.sortedByDescending { scoreList[it] } // 점수기준으로 내림차순 정렬
        Glide.with(this)
            .asGif()
            .load(R.drawable.ic_game_rankbackground)
            .into(findViewById(R.id.background_gif))
        for (i in scoreList.indices) { // 원래 점수의 인덱스
            val originalIndex = sortedIndices[i] // 내림차순한 점수의 인덱스
            scoreList2[i] = scoreList[originalIndex] // 내림차순한 점수의 인덱스에 맞게 배열 새로 정렬
            finalTimeList2[i] = finalTimeList[originalIndex]
            modeList2[i] = modeList[originalIndex]
            numsizeList2[i] = numsizeList[originalIndex]
            monthList2[i] = monthList[originalIndex]
            dayList2[i] = dayList[originalIndex]
            hourList2[i] = hourList[originalIndex]
            minuteList2[i] = minuteList[originalIndex]
        }
        for (i in modeList2.indices) { // 한글, 영어버전에 따라 제각각으로 표시되어 있어서 현재 언어에 맞게 바꿔주기
            when (modeList2[i]) {
                "이지", "Easy" -> {
                    modeList2[i] = getString(R.string.game_mode_easy)
                }

                "기본", "Base" -> {
                    modeList2[i] = getString(R.string.game_mode_base)
                }

                "하드", "Hard" -> {
                    modeList2[i] = getString(R.string.game_mode_hard)
                }

                "지옥", "Hell" -> {
                    modeList2[i] = getString(R.string.game_mode_hell)
                }
            }
        }
        for (i in scoreList.indices) {
            if (i == 15) { // 정렬 후 15번째 기록까지만 표시하도록 하기
                break
            }
            val textView = TextView(this)
            textView.text = "${i + 1}. ${
                String.format(
                    "%06d",
                    scoreList2[i]
                )
            }${getString(R.string.game_ResetRankings_score)} | ${
                String.format(
                    "%03d",
                    finalTimeList2[i] / 1000
                )
            }.${
                String.format(
                    "%02d",
                    (finalTimeList2[i] % 1000) / 10
                )
            }${getString(R.string.game_ResetRankings_second)} | ${modeList2[i]} | ${numsizeList2[i]} | ${monthList2[i]}.${dayList2[i]} ${hourList2[i]}:${
                String.format(
                    "%02d",
                    minuteList2[i]
                )
            }"
            recordsLayout.addView(textView)
        }
    }
}

