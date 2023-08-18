package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R

class MemberDetailActivity : AppCompatActivity(), MemDelDialogInterface {
    val TAG: String = "로그"
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)

        val memberEmail = findViewById<EditText>(R.id.et_email)
        val memberRegidence = findViewById<TextView>(R.id.tv_regidence)
        val memberMbti = findViewById<TextView>(R.id.tv_mbti)
        val memberHobby = findViewById<TextView>(R.id.tv_hobby)
        val memberInterest = findViewById<TextView>(R.id.tv_interest)
        val memberBlog = findViewById<TextView>(R.id.tv_blog)
        val memberGithub = findViewById<TextView>(R.id.tv_github)
        val memberSaying = findViewById<TextView>(R.id.tv_saying)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val mem_email = it.data?.getStringExtra("Email")?:""
                val mem_regi = it.data?.getStringExtra("Regidence")?:""
                val mem_mbti = it.data?.getStringExtra("MBTI")?:""
                val mem_hob = it.data?.getStringExtra("Hobby")?:""
                val mem_interst = it.data?.getStringExtra("Interest")?:""
                val mem_blog = it.data?.getStringExtra("BlogLink")?:""
                val mem_git = it.data?.getStringExtra("GithubLink")?:""
                val mem_say = it.data?.getStringExtra("Saying")?:""

                memberEmail.setText(mem_email)
                memberRegidence.setText(mem_regi)
                memberMbti.setText(mem_mbti)
                memberHobby.setText(mem_hob)
                memberInterest.setText(mem_interst)
                memberBlog.setText(mem_blog)
                memberGithub.setText(mem_git)
                memberSaying.setText(mem_say)
            }
        }


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
