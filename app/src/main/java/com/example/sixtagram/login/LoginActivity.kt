package com.example.sixtagram.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sixtagram.R
import com.example.sixtagram.Splash2Activity
import com.example.sixtagram.data.Member
import com.example.sixtagram.data.MemberHashmap

class LoginActivity : AppCompatActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onRestart() {
        super.onRestart()

        email.text.clear()
        password.text.clear()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val memberList = Member.retrieveMembers()
        var i: Int?
        MemberHashmap.memberHash()
        email = findViewById(R.id.email2)
        password = findViewById(R.id.password2)
        val btn1 = findViewById<Button>(R.id.login)
        val btn2 = findViewById<Button>(R.id.memberShip)
        val btn3 = findViewById<Button>(R.id.memberFind)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it. resultCode == RESULT_OK){
                val email2 = it.data?.getStringExtra("email2") ?: ""
                val password2 = it.data?.getStringExtra("password2") ?: ""
                email.setText(email2)
                password.setText(password2)
            }
        }


        btn1.setOnClickListener {
            if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
                toast("아이디와 비번을 입력 하여 주세요")
            } else {
                i = 0
//                for ((index, member2) in memberList.withIndex()) {
//                    if (member2.email == email.text.toString()) {
//                        i = index
//                        break
//                    }
//                }
                i = MemberHashmap.retrieveMemberByEmail(email.text.toString())

                if (i!= null && memberList[i!!].password == password.text.toString()) {
                    toast("로그인 완료")
                    val loginIntent = Intent(this, Splash2Activity::class.java)
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
        btn3.setOnClickListener {
            val memberFindIntent = Intent(this, MemberFindActivity::class.java)
            activityResultLauncher.launch(memberFindIntent)
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}