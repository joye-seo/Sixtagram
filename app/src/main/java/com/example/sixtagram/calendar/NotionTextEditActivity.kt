package com.example.sixtagram.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.sixtagram.R

class NotionTextEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notion_text_edit)

        val okbtn : ImageView = findViewById(R.id.okBtn)
        val notionEdt : EditText = findViewById(R.id.notionEdt)

        okbtn.setOnClickListener {
            val inputNotion = notionEdt.text.toString()
            if (inputNotion.isNullOrEmpty()){
                Toast.makeText(this, "공지사항을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val resultIntent = Intent()
                resultIntent.putExtra("notion", inputNotion)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }  // 오케이 버튼 클릭시



        val addBtn : ImageView = findViewById(R.id.addBtn)
        val promiseTxt : TextView = findViewById(R.id.promiseTxt)

        addBtn.setOnClickListener {
            promiseTxt.setText("텍스트 수정 업데이트 중입니다.")
        }





        val backbtn : ImageView = findViewById(R.id.backBtn)   //뒤로가기 버튼
        backbtn.setOnClickListener{
            finish()
        }



    }
}