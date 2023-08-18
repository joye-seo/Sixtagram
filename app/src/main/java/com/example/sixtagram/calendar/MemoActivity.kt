package com.example.sixtagram.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.sixtagram.R

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)


        val capturebtn : TextView = findViewById(R.id.captureBtn)
        capturebtn.setOnClickListener {
            Toast.makeText(this, "저장했습니다.", Toast.LENGTH_SHORT).show()
        }    //스크린샷 버튼



        val returnbtn : ImageView = findViewById(R.id.returnBtn)  //돌아가기 버튼
        returnbtn.setOnClickListener {
            finish()
        }


        val categories = arrayOf("카테고리를 골라주세요" ,"daily life", "a thing to do" ,"notion")
        val titleSpinner = findViewById<Spinner>(R.id.titleSpinner)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,categories)
        titleSpinner.setAdapter(adapter)
    }
}