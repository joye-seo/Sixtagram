package com.example.sixtagram.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sixtagram.R

class GameDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_description)

        val tv1 = findViewById<TextView>(R.id.textView1)

    }
}

// 1. 팀플하다가 머리터질거같으면 뇌빼고 머리식히라고 만든게임입니다 어쩌구
// 2. 1부터 차례대로 숫자를 눌러 모든 버튼을 없애버리면 됩니다 어쩌구
// 3. 노멀 모드 : 가장 일반적인 모드
// 4. 이지 모드 : 다음 숫자를 알려줍니다
// 5. 하드 모드 : 숫자가 주기적으로 안보이게 됩니다. 클릭은 가능합니다
// 6. 지옥 모드 : 숫자가 주기적으로 안보이며, 보이지 않을때는 클릭도 안됩니다. 그리고 주기적으로 숫자가 변경됩니다.
// 7. 이러한 내용들을 보기좋게 넣어보자.
// 8. 설명서 또는 스코어 보드에 점수계산법 넣어두자.