package com.example.sixtagram.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sixtagram.R
import java.text.SimpleDateFormat
import java.util.Date

class CommunityAdapter(
    val mContext: Context,
    val mList: ArrayList<CommunityData>
) : BaseAdapter() {

    // listview에 속한 item 전체 수
    override fun getCount(): Int {
        return mList.size
    }

    // 해당 위치에 대한 item 메소드 번호
    override fun getItem(position: Int): Any {
        return mList[position]
    }

    // 해당 위치에 대한 item id 값, 필요 없으면 0
    override fun getItemId(position: Int): Long {
        return 0
    }


    //xml과 view데이터를 연결하는 핵심 역할을 하는 메소드
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_community, null)

        val title = view.findViewById<TextView>(R.id.tv_community_title)
        val content = view.findViewById<TextView>(R.id.tv_community_content)
        val like = view.findViewById<TextView>(R.id.tv_community_like_count)
        val image = view.findViewById<ImageView>(R.id.iv_community_image)

        val community = mList[position]

        val format = SimpleDateFormat("MM월 dd일")

        Glide.with(mContext)
            .load(community.picture) .transform(MultiTransformation(FitCenter(), RoundedCorners(10)))

            .into(image)

        title.text = community.title
        content.text = community.content
        like.text = community.like.toString()

        return view

    }


}