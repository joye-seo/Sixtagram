package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class MemberModifyActivity : AppCompatActivity() {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_modify)

        val imgMemBack = findViewById<ImageView>(R.id.btn_mem_back2)
        imgMemBack.setOnClickListener {
            val intent = Intent(this, MemberActivity::class.java)
            startActivity(intent)
        }


        val save = findViewById<ImageView>(R.id.save_button)
        save.setOnClickListener {
//            // 텍스트뷰에서 수정된 정보를 가져오기
//            val modifiedEmail = findViewById<EditText>(R.id.et_email).text.toString()
//            val modifiedResidence = findViewById<TextView>(R.id.tv_regidence).text.toString()
//            // ... (나머지 수정된 정보 가져오기)
//
//            // 변경된 정보를 저장하거나 처리하는 코드 추가
//            // 예: SharedPreferences, 데이터베이스, 네트워크 요청 등
//
//            // 저장 완료 메시지 표시
            Toast.makeText(this, "변경 사항이 저장되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MemberDetailActivity::class.java)
            activityResultLauncher.launch(intent)

        }


    }
}