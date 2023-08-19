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

class MemUserAdap(val context: Context, val UserList: ArrayList<MemUser>) : BaseAdapter() {

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

        val user = UserList[position]
//val user = intent.getserializableExtra("user") as MemUser
        Glide.with(context)
            .load(user.profile) .transform(MultiTransformation(FitCenter(), RoundedCorners(500)))
            .into(profile)

//        profile.setImageResource(user.profile)
        name.text = user.name

        return view

    }

}
