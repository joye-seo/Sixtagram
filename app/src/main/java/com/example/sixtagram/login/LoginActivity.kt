package com.example.sixtagram.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.member.MemberActivity
import com.example.sixtagram.memberData.Member

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var memberList = Member.retrieveMembers()
        var i: Int

        var email = findViewById<EditText>(R.id.email2)
        var password = findViewById<EditText>(R.id.password2)
        val btn1 = findViewById<Button>(R.id.login)
        val btn2 = findViewById<Button>(R.id.memberShip)

//        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD


        btn1.setOnClickListener {
            if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
                toast("아이디와 비번을 입력 하여 주세요")
            } else {
                i = 0
                for ((index, member2) in memberList.withIndex()) {
                    if (member2.email == email.text.toString()) {
                        i = index
                        break
                    }
                }

                if (memberList[i].password == password.text.toString()) {
                    toast("로그인 완료")
                    val loginIntent = Intent(this, MemberActivity::class.java)
                    loginIntent.putExtra("index", i)
                    startActivity(loginIntent)

                } else {
                    toast("아이디 또는 비밀번호를 잘 못 입력하셨습니다.")
                }
            }
        }
        btn2.setOnClickListener {
            val memberShipIntent = Intent(this, SignUpActivity::class.java)
            startActivity(memberShipIntent)
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}