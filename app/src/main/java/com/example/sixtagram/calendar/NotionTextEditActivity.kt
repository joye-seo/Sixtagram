package com.example.sixtagram.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sixtagram.R

class NotionTextEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notion_text_edit)

        val okbtn : Button = findViewById(R.id.okBtn)
        val notionEdt : EditText = findViewById(R.id.notionEdt)

        okbtn.setOnClickListener {
            val inputNotion = notionEdt.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("notion",inputNotion)
            setResult(RESULT_OK,resultIntent)
            finish()
        }







        val backbtn : Button = findViewById(R.id.backBtn)
        backbtn.setOnClickListener{
            finish()
        }



    }
}