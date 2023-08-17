package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class MemberDetailActivity : AppCompatActivity(), MemDelDialogInterface {
    val TAG: String = "로그"
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)

        val modify = findViewById<Button>(R.id.modify_button)
        modify.setOnClickListener {
            val intent = Intent(this, MemberModifyActivity::class.java)
            activityResultLauncher.launch(intent)
        }
        val delete = findViewById<Button>(R.id.delete_button)
        delete.setOnClickListener {
            val intent = Intent(this, MemberModifyActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    fun onDialogBtnClicked(view: View) {
        Log.d(TAG, "MemberDetailActivity - onDialogBtnClicked() called")

        val memDelDialog = MemDelDialogActivity(this, this)
        memDelDialog.show()

    }

    override fun ondelYesMemBtnClicked() {
        Toast.makeText(this, "삭제버튼 클릭", Toast.LENGTH_SHORT).show()

    }//삭제 버튼 클릭

    override fun ondelNoMemBtnClicked() {
        Toast.makeText(this, "삭제취소 클릭", Toast.LENGTH_SHORT).show()

    }//삭제 취소 클릭
}
