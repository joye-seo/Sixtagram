package com.example.sixtagram.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.sixtagram.R

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)



        val returnbtn : ImageView = findViewById(R.id.returnBtn)  //돌아가기 버튼
        returnbtn.setOnClickListener {
            finish()
        }
    }
}