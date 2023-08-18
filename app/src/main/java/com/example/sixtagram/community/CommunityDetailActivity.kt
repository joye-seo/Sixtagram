package com.example.sixtagram.community

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sixtagram.R
import com.example.sixtagram.data.Comment
import com.example.sixtagram.data.CommunityData

class CommunityDetailActivity : AppCompatActivity() {

    lateinit var commentAdapter: CommentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        val commentList = Comment.commentList

        commentAdapter = CommentAdapter(this, commentList)

        val btnBackDetail = findViewById<ImageView>(R.id.btn_community_detail_back)
        val btnModifyDetail = findViewById<ImageView>(R.id.btn_community_detail_modify)
        val btnDeleteDetail = findViewById<ImageView>(R.id.btn_community_detail_delete)
        val tvTitleDetail = findViewById<TextView>(R.id.tv_community_detail_title)
        val tvContentDetail = findViewById<TextView>(R.id.tv_community_detail_content)
        val imageContentDetail = findViewById<ImageView>(R.id.iv_community_detail_image)
        val scroll = findViewById<NestedScrollView>(R.id.community_detail_scroll)
        val container = findViewById<LinearLayout>(R.id.commend_container)


        //댓글 관련 구현한 로직 추가적으로 공부해봐야 함!!
//        commentList.forEach {
//            val view = LayoutInflater.from(this).inflate(R.layout.item_community_comment, null, false)
//            val textView = view.findViewById<TextView>(R.id.tv_community_comment_content)
//            textView.text = it.comment
//            container.addView(view)
//        }

        val commentListView = findViewById<ListView>(R.id.listview_comment)
        commentListView.adapter = commentAdapter

        btnBackDetail.setOnClickListener {
//            val intent = Intent(this,CommunityActivity::class.java)
//            startActivity(intent)
            finish()
        }
//        commentListView.isNestedScrollingEnabled = true

        val communityData = intent.getSerializableExtra("communityData") as CommunityData
        tvTitleDetail.text = communityData.title
        tvContentDetail.text = communityData.content
        Glide.with(this).load(communityData.picture).transform(MultiTransformation(FitCenter(), RoundedCorners(10)))
            .into(imageContentDetail)


    }
}