package com.example.sixtagram.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sixtagram.R
import com.example.sixtagram.memberData.Member
import com.example.sixtagram.memberData.MemberHashmap

class MemberFindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_find)

        val memberHashMap = MemberHashmap.retrieveAllMembers()
        val residence = findViewById<EditText>(R.id.residence)
        val btn1 = findViewById<Button>(R.id.memberFind)
        val btn2 = findViewById<Button>(R.id.close)
        val emailView = findViewById<TextView>(R.id.emailView)
        val passwordView = findViewById<TextView>(R.id.passwordView)

        val memberList = Member.retrieveMembers()
        val keys: Set<String> = memberHashMap.keys
        var i: Int
        btn1.setOnClickListener {
            if (residence.text.isNullOrEmpty()) {
                toast("거주지를 입력하여주세요")
            } else {
                i = 0
                for ((index, member) in memberList.withIndex()) {
                    if (member.residence == residence.text.toString()) {
                        emailView.text = keys.toString()
                        passwordView.text = memberList[index].password
                        break
                    }
                    i++
                }
                if (i >= memberList.size){
                    toast("거주지를 잘못 입력하였습니다.")
                }
            }
        }
        btn2.setOnClickListener {
            val homeIntent = Intent(this, LoginActivity::class.java)
                .putExtra("email2", emailView.text.toString())
                .putExtra("password2", passwordView.text.toString())
            setResult(RESULT_OK, homeIntent)
            finish()
        }
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}