package com.example.sixtagram.game

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Calendar

class GameEndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameend)
//        intent.getStringExtra("name")
        val score = intent.getLongExtra("score", 0L)
        val finalTime = intent.getLongExtra("finalTime", 0L)
        val mode = intent.getStringExtra("mode")
        val numsize = intent.getStringExtra("numsize")
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1  // MONTH는 0부터 시작하므로 1을 더해줍니다.
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val btdd = findViewById<Button>(R.id.button1)
        saveGameData(score, finalTime, mode, numsize,month,day,hour,minute)
        displayGameData()
        btdd.setOnClickListener {
            val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            displayGameData()
        }
    }


    private fun saveGameData(score: Long, finalTime: Long, mode: String?, numsize: String?,month:Int,day:Int,hour:Int,minute:Int) {
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
        val minuteList = Gson().fromJson<List<Int>>(existingminutes, typeTokenminute).toMutableList()

        // 새로운 데이터를 리스트에 추가합니다.

        scoreList.add(score)
        finalTimeList.add(finalTime)
        modeList.add(mode)
        numsizeList.add(numsize)
        monthList.add(month)
        dayList.add(day)
        hourList.add(hour)
        minuteList.add(minute)
        if ((scoreList[scoreList.size - 1] == 0L)) { // 게임 끝나기 전에 데이터 추가하면 바로 삭제해주기
            scoreList.remove(score)
            finalTimeList.remove(finalTime)
            modeList.remove(mode)
            numsizeList.remove(numsize)
            monthList.remove(month)
            dayList.remove(day)
            hourList.remove(hour)
            minuteList.remove(minute)
        } else if (scoreList.size >= 2) {
            if ((scoreList[scoreList.size - 1] == scoreList[scoreList.size - 2])) // 이전 데이터랑 같은 데이터 추가되면 바로 삭제해주기
            {
                scoreList.remove(score)
                finalTimeList.remove(finalTime)
                modeList.remove(mode)
                numsizeList.remove(numsize)
                monthList.remove(month)
                dayList.remove(day)
                hourList.remove(hour)
                minuteList.remove(minute)
            }
        }

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
        val scoreList2 = MutableList<Long>(scoreListSize) { 0L }
        val finalTimeList2 = MutableList<Long>(scoreListSize) { 0L }
        val modeList2 = MutableList<String?>(scoreListSize) { "" }
        val numsizeList2 = MutableList<String?>(scoreListSize) { "" }
        val monthList2 = MutableList<Int>(scoreListSize) { 0 }
        val dayList2 = MutableList<Int>(scoreListSize) { 0 }
        val hourList2 = MutableList<Int>(scoreListSize) { 0 }
        val minuteList2 = MutableList<Int>(scoreListSize) { 0 }
//
        val sortedIndices = scoreList.indices.sortedByDescending { scoreList[it] } // 점수기준으로 내림차순 정렬

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
        for(i in modeList2.indices)
        {
            when(modeList2[i])
            {
                "이지" -> {getString(R.string.game_mode_easy)}
                "기본" -> {getString(R.string.game_mode_base)}
                "하드" -> {getString(R.string.game_mode_hard)}
                "지옥" -> {getString(R.string.game_mode_hell)}
            }
        }
        for (i in scoreList.indices) {
            val textView = TextView(this)
            textView.text = "${i + 1}. ${String.format("%06d", scoreList2[i])}점 | ${
                String.format(
                    "%03d",
                    finalTimeList2[i] / 1000
                )
            }.${
                String.format(
                    "%03d",
                    finalTimeList2[i] % 1000
                )
            }초 | ${modeList2[i]} | ${numsizeList2[i]} | ${monthList2[i]}.${dayList2[i]} ${hourList2[i]}:${String.format("%02d",minuteList2[i])}"
            recordsLayout.addView(textView)
        }
    }

}

// 1. 스코어 화면
// 2. 다시하기
// 3. 종료하기 등등
// 4. 오름차순 정렬
// 5. 데이터 길이 맞추기
// 6. 점수 시간 모드 등등 해보기
// 7. 넘버사이즈도 넣기
// 8. 나중에 현재 로그인 사용자 이름도 받아서 넣기
// 9. 백그라운드틴트를 적용해야함 버튼
// 4. 리스트 오름차순 정렬 어떻게?
// 10. 같은 기록 처리방안 만들기
// 11. 10위까지만 저장하기. index가 11이 넘어가면, 오름차순 정리후 11번째 삭제 추가하기
