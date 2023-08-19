package com.example.sixtagram.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.data.CommunityData

class CommunityAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_add)

        val btnBackAdd = findViewById<ImageView>(R.id.btn_community_add_back)
        val btnSaveAdd = findViewById<ImageView>(R.id.btn_community_add_save)

        val edtTitle = findViewById<EditText>(R.id.edt_comment_add_title)
        val edtContent = findViewById<EditText>(R.id.edt_comment_add_content)

        btnBackAdd.setOnClickListener {
            finish()
        }

        btnSaveAdd.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            val test = CommunityData("", edtTitle.toString(), edtContent.toString(), "", 0)
            intent.putExtra("addData", test)
            setResult(RESULT_OK, intent)
            finish()

            Log.d("test", test.title)
        }


    }
}