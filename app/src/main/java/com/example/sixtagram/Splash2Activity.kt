package com.example.sixtagram

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.databinding.ActivitySplash2Binding
import com.example.sixtagram.member.MemberActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private lateinit var binding: ActivitySplash2Binding

class Splash2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val localTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(" MM월 dd일 HH시 mm분")
        val time = localTime.format(formatter)


        binding.tvTime.text = "현재 시간 $time"

        ObjectAnimator.ofFloat(binding.splash2, View.TRANSLATION_X, -590f).apply {
            duration = 2000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            start()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            moveScreen()
        }, 1800)


    }

    private fun moveScreen() {
        val intent = Intent(this, MemberActivity::class.java)
        startActivity(intent)
        finish()
    }
}
