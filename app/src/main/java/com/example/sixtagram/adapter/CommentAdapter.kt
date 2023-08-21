package com.example.sixtagram.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sixtagram.R
import com.example.sixtagram.data.Comment.commentList
import com.example.sixtagram.data.CommentData

class CommentAdapter(
    val mContext: Context,
    var mList: ArrayList<CommentData>
) : BaseAdapter() {

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun saveItem (){
        mList = commentList
        this.notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_community_comment, null)

        val comment = view.findViewById<TextView>(R.id.tv_community_comment_content)
        val commentList = mList[position]

        comment.text = commentList.comment



        return view

    }
}