package com.example.sixtagram.member

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sixtagram.R
import com.example.sixtagram.memberData.Member.members
import com.example.sixtagram.memberData.MemberData

class MemUserAdap(val context: Context, val UserList: MutableList<MemberData>) : BaseAdapter() {

    override fun getCount(): Int {
        return UserList.size

    }

    override fun getItem(position: Int): Any {
        return UserList[position]

    }

    override fun getItemId(position: Int): Long {
        return 0

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.activity_memuseractivity, null)

        val profile = view.findViewById<ImageView>(R.id.iv_profile_kms)
        val name = view.findViewById<TextView>(R.id.tv_name)
//        val password = view.findViewById<TextView>(R.id.tv_name)
//        val residence = view.findViewById<TextView>(R.id.tv_name)
//        val mbti = view.findViewById<TextView>(R.id.tv_name)
//        val hobby = view.findViewById<TextView>(R.id.tv_name)
//        val concern = view.findViewById<TextView>(R.id.tv_name)
//        val blog = view.findViewById<TextView>(R.id.tv_name)
//        val github = view.findViewById<TextView>(R.id.tv_name)
//        val text = view.findViewById<TextView>(R.id.tv_comment)

        val user = members[position]
//        val user = intent.getserializableExtra("user") as Member
        Glide.with(context)
            .load(user.imageEdit) .transform(MultiTransformation(FitCenter(), RoundedCorners(500)))
            .into(profile)

//        profile.setImageResource(user.profile)
        name.text = user.name

        return view

    }

}
