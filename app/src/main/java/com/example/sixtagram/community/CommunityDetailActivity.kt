package com.example.sixtagram.community

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        val btnBackDetail = findViewById<ImageView>(R.id.btn_community_detail_back)
        val btnModifyDetail = findViewById<ImageView>(R.id.btn_community_detail_modify)
        val btnDeleteDetail = findViewById<ImageView>(R.id.btn_community_detail_delete)

        val tvTitleDetail = findViewById<TextView>(R.id.tv_community_detail_title)
        val tvContentDetail = findViewById<TextView>(R.id.tv_community_detail_content)

        btnBackDetail.setOnClickListener {
//            val intent = Intent(this,CommunityActivity::class.java)
//            startActivity(intent)
            finish()
        }

        val communityData = intent.getSerializableExtra("communityData") as CommunityData
        tvTitleDetail.text = communityData.title
        tvContentDetail.text = communityData.content


    }
}