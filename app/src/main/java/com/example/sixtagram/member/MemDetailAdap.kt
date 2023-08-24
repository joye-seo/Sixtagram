//package com.example.sixtagram.member
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import com.example.sixtagram.R
//import com.example.sixtagram.data.Member
//
//class MemDetailAdap(val context: Context, val UserList: MutableList<Member>) : BaseAdapter() {
//
//    override fun getCount(): Int {
//        return UserList.size
//
//    }
//
//    override fun getItem(position: Int): Any {
//        return UserList[position]
//
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View =
//            LayoutInflater.from(context).inflate(R.layout.activity_memuseractivity, null)
//
//