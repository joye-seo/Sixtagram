package com.example.sixtagram.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.memberData.Member

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val memberList = Member.retrieveMembers()
        val email = findViewById<EditText>(R.id.email2)
        val password = findViewById<EditText>(R.id.password)
        val password2 = findViewById<EditText>(R.id.password2)
        val name = findViewById<EditText>(R.id.name)
        val residence = findViewById<EditText>(R.id.residence)
        val mbti = findViewById<EditText>(R.id.mbti)
        val hobby = findViewById<EditText>(R.id.hobby)
        val concern = findViewById<EditText>(R.id.concern)
        val blog = findViewById<EditText>(R.id.blog)
        val github = findViewById<EditText>(R.id.github)
        val text = findViewById<EditText>(R.id.editText)
        val idCheck = findViewById<Button>(R.id.emailOverlap2)
        val passwordCheck = findViewById<Button>(R.id.passOverlap)
        val idText = findViewById<TextView>(R.id.emailCheck)
        var passwordText = findViewById<TextView>(R.id.passwordCheck)
        val passwordText2 = findViewById<TextView>(R.id.passwordCheck2)
        val addMember = findViewById<Button>(R.id.createMember)
        var i: Int = 0
        var k: Boolean = false

        idCheck.setOnClickListener {
            i = 0
            for (member in memberList) {
                if (member.email == email.text.toString()) {
                    idText.text = "중복된 아이디가 존재 합니다!!"
                    break
                }
                i++
            }
            if (i == memberList.size) {
                idText.text = "사용 가능한 아이디 입니다."
            }

        }

        passwordCheck.setOnClickListener {
            if (password.text.toString() == password2.text.toString()) {
                passwordText2.text = "비밀 번호가 일치 합니다."
                k = true
            } else {
                passwordText2.text = "비밀 번호가 불일치 합니다."
                k = false
            }
        }



        addMember.setOnClickListener {
            if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty() || password2.text.isNullOrEmpty() || name.text.isNullOrEmpty() || i < memberList.size || !k) {
                toast("필수 정보 또는 id 체크와 비밀 번호를 확인해 주세요")
            } else run {
                Member.createMember(
                    email.text.toString(),
                    password2.text.toString(),
                    name.text.toString(),
                    residence.text.toString(),
                    mbti.text.toString(),
                    hobby.text.toString(),
                    concern.text.toString(),
                    blog.text.toString(),
                    github.text.toString(),
                    text.text.toString()
                )
                toast("회원 가입 완료")
                finish()
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}