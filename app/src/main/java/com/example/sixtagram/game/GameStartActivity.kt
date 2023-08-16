package com.example.sixtagram.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sixtagram.R

class GameStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamestart)

        val btSt = findViewById<Button>(R.id.button1)
        val btLv = findViewById<Button>(R.id.button2)
        val btm = findViewById<Button>(R.id.button3)
        var numsize: String = "1~25" // "1~25" "1~50"
        var mode: String = "노멀 모드" // "easy" "normal"

        btSt.setOnClickListener {
            val intent2 = Intent(this,GameMainActivitiy::class.java)
            intent2.putExtra("numsize",numsize)
            intent2.putExtra("mode",mode)
            startActivity(intent2)
        }

        btLv.setOnClickListener {
            if (numsize == "1~25") {
                numsize = "1~50"
            } else if (numsize == "1~50") {
                numsize = "1~25"
            }
            btLv.setText("숫자 크기 : ${numsize}")
        }

        btm.setOnClickListener {
            when (mode) {
                "노멀 모드" -> {
                    mode = "이지 모드"
                }

                "이지 모드" -> {
                    mode = "노멀 모드"
                }
            }
            btm.setText("${mode}")
        }
    }
}