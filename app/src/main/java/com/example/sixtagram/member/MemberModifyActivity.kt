package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class MemberModifyActivity : AppCompatActivity() {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_modify)

        val save = findViewById<TextView>(R.id.save_button)
        save.setOnClickListener {
            val intent = Intent(this, MemberDetailActivity::class.java)
            activityResultLauncher.launch(intent)

        }


    }
}