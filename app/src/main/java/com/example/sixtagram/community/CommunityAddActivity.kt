package com.example.sixtagram.community

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class CommunityAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_add)

        val btnBackAdd = findViewById<ImageView>(R.id.btn_community_add_back)

        btnBackAdd.setOnClickListener {
            finish()
        }

    }
}