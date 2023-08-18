package com.example.sixtagram.member

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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


        val imgMemBack = findViewById<ImageView>(R.id.btn_mem_back)
        imgMemBack.setOnClickListener {
            val intent = Intent(this, MemberActivity::class.java)
            startActivity(intent)
        }


        val memberEmail = findViewById<EditText>(R.id.et_email)
        val memberRegidence = findViewById<TextView>(R.id.tv_regidence)
        val memberMbti = findViewById<TextView>(R.id.tv_mbti)
        val memberHobby = findViewById<TextView>(R.id.tv_hobby)
        val memberInterest = findViewById<TextView>(R.id.tv_interest)
        val memberBlog = findViewById<TextView>(R.id.tv_blog)
        val memberGithub = findViewById<TextView>(R.id.tv_github)
        val memberComment = findViewById<TextView>(R.id.tv_saying)

        val intent = intent
        val selectedUser = intent.getSerializableExtra("MemUser") as? MemUser // null-safe cast

        // 선택한 사용자 정보가 null이 아닌 경우에만 데이터를 표시하도록 처리
        if (selectedUser != null) {
            // 데이터를 해당 뷰에 표시하는 코드
            memberEmail.setText(selectedUser.email)
            memberRegidence.text = selectedUser.regidence
            memberMbti.text = selectedUser.mbti
            memberHobby.text = selectedUser.hobby
            memberInterest.text = selectedUser.interest
            memberBlog.text = selectedUser.blog
            memberGithub.text = selectedUser.github
            memberComment.text = selectedUser.comment
        } else {
            // 선택한 사용자 정보가 null인 경우, 에러 처리 등을 수행
        }

        // 나머지 코드...
    }

    override fun ondelYesMemBtnClicked() {
        Toast.makeText(this, "삭제버튼 클릭", Toast.LENGTH_SHORT).show()
    }

    override fun ondelNoMemBtnClicked() {
        Toast.makeText(this, "삭제취소 클릭", Toast.LENGTH_SHORT).show()
    }


//        activityResultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                if (it.resultCode == RESULT_OK) {
//                    val mem_email = it.data?.getStringExtra("Email") ?: ""
//                    val mem_regi = it.data?.getStringExtra("Regidence") ?: ""
//                    val mem_mbti = it.data?.getStringExtra("MBTI") ?: ""
//                    val mem_hob = it.data?.getStringExtra("Hobby") ?: ""
//                    val mem_interst = it.data?.getStringExtra("Interest") ?: ""
//                    val mem_blog = it.data?.getStringExtra("BlogLink") ?: ""
//                    val mem_git = it.data?.getStringExtra("GithubLink") ?: ""
//                    val mem_com = it.data?.getStringExtra("Comment") ?: ""
//
//                    memberEmail.setText(mem_email)
//                    memberRegidence.setText(mem_regi)
//                    memberMbti.setText(mem_mbti)
//                    memberHobby.setText(mem_hob)
//                    memberInterest.setText(mem_interst)
//                    memberBlog.setText(mem_blog)
//                    memberGithub.setText(mem_git)
//                    memberComment.setText(mem_com)
//                }
//            }


//        val modify = findViewById<Button>(R.id.modify_button)
//        modify.setOnClickListener {
//            val intent = Intent(this, MemberModifyActivity::class.java)
//            activityResultLauncher.launch(intent)
//        }
//        val delete = findViewById<Button>(R.id.delete_button)
//        delete.setOnClickListener {
//            val intent = Intent(this, MemberModifyActivity::class.java)
//            activityResultLauncher.launch(intent)
//        }
}

//    fun onDialogBtnClicked(view: View) {
//        Log.d(TAG, "MemberDetailActivity - onDialogBtnClicked() called")
//
//        val memDelDialog = MemDelDialogActivity(this, this)
//        memDelDialog.show()
//
//    }
//
//    override fun ondelYesMemBtnClicked() {
//        Toast.makeText(this, "삭제버튼 클릭", Toast.LENGTH_SHORT).show()
//
//    }//삭제 버튼 클릭
//
//    override fun ondelNoMemBtnClicked() {
//        Toast.makeText(this, "삭제취소 클릭", Toast.LENGTH_SHORT).show()
//
//    }//삭제 취소 클릭
