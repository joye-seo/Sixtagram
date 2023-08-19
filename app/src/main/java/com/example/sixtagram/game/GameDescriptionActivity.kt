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
//위는 스타트페이지 아래는 게임,모드에대한 설명
// 2. 1부터 차례대로 숫자를 눌러 모든 버튼을 없애버리면 됩니다 어쩌구
// 3. 이지 모드 : 다음 숫자를 다른색으로 표시합니다.
// 4. 노멀 모드 : 가장 일반적인 모드
// 5. 하드 모드 : 숫자가 주기적으로 안보이게 됩니다. 클릭은 가능합니다
// 6. 지옥 모드 : 숫자가 주기적으로 안보이며, 보이지 않을때는 클릭도 안됩니다. 그리고 주기적으로 숫자가 변경됩니다.
// 7. 이러한 내용들을 보기좋게 넣어보자.
// 8. 설명서 또는 스코어 보드에 어려운 모드일수록 점수가 높아져요.
/*
게임 목표 : 숫자를 차례대로 눌러 모든 버튼을 없애버리면 됩니다.

        게임 모드 : 이지, 기본, 하드, 지옥
        이지 : 다음 숫자를 다른색으로 표시합니다.
        기본 : 기본이 되는 모드
        하드 : 숫자가 주기적으로 가려집니다. 클릭은 가능합니다.
        지옥 : 숫자가 주기적으로 가려집니다. 가려지면 클릭도 안됩니다.
9. 패딩 수평으로주는거 줘서 양쪾 여백 어느정도 만들어주기


*/