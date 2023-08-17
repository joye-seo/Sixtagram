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

class GameEndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameend)
//        intent.getStringExtra("name")
        val score = intent.getLongExtra("score",0L)
        val finalTime = intent.getLongExtra("finalTime",0L)
        val mode = intent.getStringExtra("mode")
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val btdd = findViewById<Button>(R.id.button1)
        saveGameData(score, finalTime, mode)
        displayGameData()
        btdd.setOnClickListener {
            val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            displayGameData()
        }
    }


    private fun saveGameData(score: Long, finalTime: Long, mode:String?) {
        val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // 기존의 score 데이터 리스트를 가져옵니다.
        val existingScores = sharedPref.getString("scores", "[]")
        val typeTokenScore = object : TypeToken<List<Long>>() {}.type
        val scoreList = Gson().fromJson<List<Long>>(existingScores, typeTokenScore).toMutableList()

        // 기존의 finalTime 데이터 리스트를 가져옵니다.
        val existingFinalTimes = sharedPref.getString("finalTimes", "[]")
        val typeTokenFinalTime = object : TypeToken<List<Long>>() {}.type
        val finalTimeList = Gson().fromJson<List<Long>>(existingFinalTimes, typeTokenFinalTime).toMutableList()

        // 기존의 mode 데이터 리스트를 가져옵니다.
        val existingmodes = sharedPref.getString("modes", "[]")
        val typeTokenmode = object : TypeToken<List<String?>>() {}.type
        val modeList = Gson().fromJson<List<String?>>(existingmodes, typeTokenmode).toMutableList()
        // 새로운 데이터를 리스트에 추가합니다.

        scoreList.add(score)
        finalTimeList.add(finalTime)
        modeList.add(mode)
        if((scoreList[scoreList.size-1]==0L)){
            scoreList.remove(score)
            finalTimeList.remove(finalTime)
            modeList.remove(mode)
        }
        else if(scoreList.size>=2)
        {
            if((scoreList[scoreList.size-1]==scoreList[scoreList.size-2]))
            {
                scoreList.remove(score)
                finalTimeList.remove(finalTime)
                modeList.remove(mode)
            }
        }

        // 리스트를 다시 JSON 문자열로 변환하여 저장합니다.
        editor.putString("scores", Gson().toJson(scoreList))
        editor.putString("finalTimes", Gson().toJson(finalTimeList))
        editor.putString("modes", Gson().toJson(modeList))
        editor.apply()
    }

    private fun displayGameData() {
        val sharedPref = getSharedPreferences("game_records", Context.MODE_PRIVATE)

        val existingScores = sharedPref.getString("scores", "[]")
        val scoreList = Gson().fromJson<List<Long>>(existingScores, object : TypeToken<List<Long>>() {}.type)

        val existingFinalTimes = sharedPref.getString("finalTimes", "[]")
        val finalTimeList = Gson().fromJson<List<Long>>(existingFinalTimes, object : TypeToken<List<Long>>() {}.type)

        val existingmodes = sharedPref.getString("modes", "[]")
        val modeList = Gson().fromJson<List<String?>>(existingmodes, object : TypeToken<List<String?>>() {}.type)

        val recordsLayout = findViewById<LinearLayout>(R.id.recordsLayout)
        recordsLayout.removeAllViews() // 기존에 추가된 뷰 제거

        for (i in scoreList.indices) {
            val textView = TextView(this)
            textView.text = "${scoreList[i]} & ${finalTimeList[i]/1000}.${finalTimeList[i]%1000} & ${modeList[i]}"
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
