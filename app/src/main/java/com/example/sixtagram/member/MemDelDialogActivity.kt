package com.example.sixtagram.member

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.sixtagram.R

class MemDelDialogActivity(context: Context, delDialogInterface: MemDelDialogInterface) : Dialog(context), View.OnClickListener {


    val TAG: String = "로그"

    private var delYesMember: MemDelDialogInterface? = null

    //인터페이스 연결
    init {
        this.delYesMember = delYesMember

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mem_del_dialog)

        Log.d(TAG, "MemDelDialogActivity - onCreate() called")
        //transparent background
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val del_yes = findViewById<Button>(R.id.del_yes)
        val del_no = findViewById<Button>(R.id.del_no)

        del_yes.setOnClickListener {
            Log.d(TAG, "MemDelDialogActivity - 삭제 확인 버튼 클릭!")
            this.delYesMember?.ondelYesMemBtnClicked()
        }        //삭제 확인 버튼
        del_no.setOnClickListener {
            Log.d(TAG, "MemDelDialogActivity - 삭제 확인 버튼 클릭!")
            this.delYesMember?.ondelNoMemBtnClicked()
        }       //삭제 취소 버튼
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}