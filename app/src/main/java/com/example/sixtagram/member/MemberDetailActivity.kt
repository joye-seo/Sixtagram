package com.example.sixtagram.member

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sixtagram.R
import com.example.sixtagram.memberData.MemberData

class MemberDetailActivity : AppCompatActivity(), MemDelDialogInterface {
    val TAG: String = "로그"
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
//    lateinit var MemDetailAdap: MemDetailAdap//멤버디테일액티 셀렉터할거

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)




        val imgMemBack = findViewById<ImageView>(R.id.btn_mem_back)
        imgMemBack.setOnClickListener {
            val intent = Intent(this, MemberActivity::class.java)
            startActivity(intent)
        }

        val modifyButton = findViewById<ImageView>(R.id.modify_button)
        modifyButton.setOnClickListener {
            val intent = Intent(this, MemberModifyActivity::class.java)
            startActivity(intent)
        }


        val linkBlog = findViewById<TextView>(R.id.tv_blog)
        linkBlog.setOnClickListener {
            val blogLink = linkBlog.text.toString() // 또는 링크 정보를 가져오는 방식에 맞게 수정
            if (blogLink.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(blogLink))
                startActivity(intent)
            }
        }
        val linkGithub = findViewById<TextView>(R.id.tv_github)
        linkGithub.setOnClickListener {
            val githubLink = linkGithub.text.toString() // 또는 링크 정보를 가져오는 방식에 맞게 수정
            if (githubLink.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubLink))
                startActivity(intent)
            }
        }


        val memberEmail = findViewById<TextView>(R.id.et_email)
        val memberRegidence = findViewById<TextView>(R.id.tv_regidence)
        val memberMbti = findViewById<TextView>(R.id.tv_mbti)
        val memberHobby = findViewById<TextView>(R.id.tv_hobby)
        val memberInterest = findViewById<TextView>(R.id.tv_interest)
        val memberBlog = findViewById<TextView>(R.id.tv_blog)
        val memberGithub = findViewById<TextView>(R.id.tv_github)
        val memberComment = findViewById<TextView>(R.id.tv_saying)
        val memberImage = findViewById<ImageView>(R.id.imageView2)
        val memberName = findViewById<TextView>(R.id.textView9)

        val intent = intent
        val selectedUser = intent.getSerializableExtra("MemberData") as? MemberData

        if (selectedUser != null) {
            // 선택한 사용자 정보를 화면에 표시하는 코드
            memberRegidence.setText(selectedUser.residence)
            memberMbti.text = selectedUser.mbti
            memberHobby.text = selectedUser.hobby
            memberInterest.text = selectedUser.concern
            memberBlog.text = selectedUser.blog
            memberGithub.text = selectedUser.github
            memberComment.text = selectedUser.text
            memberName.text = selectedUser.name
            Glide.with(this)
                .load(selectedUser.imageEdit) .transform(MultiTransformation(FitCenter(), RoundedCorners(500)))
                .into(memberImage)
        } else {
            // 선택한 사용자 정보가 없을 때의 처리
        }

    }

    // 삭제 확인 다이얼로그를 띄우는 함수
    override fun showDeleteConfirmationDialog() {
        val deleteDialog = MemDelDialogActivity(this, this)
        deleteDialog.show()
    }

    // MemDelDialogInterface의 콜백 메서드 구현
    override fun ondelYesMemBtnClicked() {
//        adapter.remove(selectedUser) // 어댑터에서 해당 항목 제거
//        adapter.notifyDataSetChanged() // 리스트뷰 갱신
        // 여기에 삭제 작업 수행
        // 예를 들어 데이터베이스에서 해당 멤버를 삭제하는 등의 작업
        // 삭제 작업 후 MemberActivity로 돌아가도록 Intent 등으로 처리
        // 삭제 작업 후 MemberActivity로 돌아가는 코드가 필요합니다.
    }

    override fun ondelNoMemBtnClicked() {
        // 삭제 취소 버튼 클릭 시 동작
        // 여기에 취소 시 동작을 구현합니다.
    }


}
