package com.example.sixtagram.calander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.sixtagram.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CalanderActivity : AppCompatActivity() {

    lateinit var mRvDiary : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calander)

        mRvDiary = findViewById(R.id.recycler_diary)

        val btnBlueWrite : FloatingActionButton = findViewById(R.id.btn_blue_write)
        btnBlueWrite.setOnClickListener {



        }
    }
}
