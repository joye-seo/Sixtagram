package com.example.sixtagram.calendar

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sixtagram.R

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)


        val captureBtn : TextView = findViewById(R.id.captureBtn)
        captureBtn.setOnClickListener {
            val toastMessage = getString(R.string.toastCapture)
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }     //캡쳐 버튼



        val returnbtn : ImageView = findViewById(R.id.returnBtn)  //돌아가기 버튼
        returnbtn.setOnClickListener {
            finish()
        }


        val categories = arrayOf(getString(R.string.choose_category), "Daily life", "A thing to do", "Notion")
        val titleSpinner = findViewById<Spinner>(R.id.titleSpinner)
        val adapter = ArrayAdapter(this,R.layout.spinner_item,categories)
        titleSpinner.setAdapter(adapter)        //카테고리



        val deleteBtn : ImageView = findViewById(R.id.deleteBtn)
        deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.deletetxt))
            builder.setMessage(getString(R.string.do_deletetxt))

            builder.setNegativeButton(getString(R.string.ok_delete)) {_ , _ ->
                Toast.makeText(this, getString(R.string.delete_go) , Toast.LENGTH_SHORT).show()
            }

            builder.setPositiveButton(getString(R.string.no_delete)) {_ , _ ->
                Toast.makeText(this , getString(R.string.no_do_delete) , Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }


    }
}