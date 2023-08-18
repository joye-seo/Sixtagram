package com.example.sixtagram.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.memberData.Member
import com.example.sixtagram.memberData.MemberHashmap

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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
        val idText = findViewById<TextView>(R.id.emailCheck)
        val passwordText = findViewById<TextView>(R.id.passwordCheck)
        val passwordText2 = findViewById<TextView>(R.id.passwordCheck2)
        val addMember = findViewById<Button>(R.id.createMember)
        val memberHashMap = MemberHashmap.retrieveAllMembers()
        val memberList = Member.retrieveMembers()

        val pattern =
            "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#\$%^&*()_+])[A-Za-z\\d@#\$%^&*()_+]+$".toRegex()
        var k = false
        var j = false
        val minLength = 6
        val i = memberList.size


        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (password.text.toString() == password2.text.toString()) {
                    passwordText2.text = "비밀 번호가 일치 합니다."
                    k = true
                } else {
                    passwordText2.text = "비밀 번호가 불일치 합니다."
                    k = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        password2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText = password.text.toString()

                if (password.text.toString() == password2.text.toString()) {
                    passwordText2.text = "비밀 번호가 일치 합니다."
                    k = true
                } else {
                    passwordText2.text = "비밀 번호가 불일치 합니다."
                    k = false
                }
                if (inputText.matches(pattern) && (s?.length ?: 0) > minLength) {
                    // Valid input: Contains a combination of letters, digits, and special characters
                    passwordText.text = "사용 가능한 비밀 번호 입니다."
                    j = true
                } else {
                    passwordText.text = "영문자, 숫자, 특수문자로 6자리 이상 입력해주세요"
                    j = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        idCheck.setOnClickListener {
            val key = email.text.toString()
//            for (member in memberList) {
//                if (member.email == email.text.toString()) {
//                    idText.text = "중복된 아이디가 존재 합니다!!"
//                    break
//                }
//                i++
//            }
//            if (i == memberList.size) {
//                idText.text = "사용 가능한 아이디 입니다."
//            }
            if (memberHashMap.containsKey(key)) {
                idText.text = "중복된 아이디가 존재 합니다!!"
            } else {
                idText.text = "사용 가능한 아이디 입니다."
            }

        }

        addMember.setOnClickListener {
            if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty() || password2.text.isNullOrEmpty() || name.text.isNullOrEmpty() || !k || !j) {
                toast("필수 정보 또는 id 체크와 비밀 번호를 확인해 주세요")
            } else run {
                Member.createMember(
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
                MemberHashmap.memberHashAdd(email.text.toString(), i)
                toast(i.toString())
//                toast("회원 가입 완료")
                finish()
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}