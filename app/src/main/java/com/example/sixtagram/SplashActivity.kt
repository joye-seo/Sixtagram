package com.example.sixtagram

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.example.sixtagram.community.CommunityActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        actionSplash()
    }

    private fun actionSplash() {
        Handler().postDelayed(2500) {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}