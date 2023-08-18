package com.example.sixtagram.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.airbnb.lottie.LottieAnimationView
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

        var heart = false
        var likeCount = 0
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

        val btnHeart = findViewById<LinearLayout>(R.id.layout_heart_click)

        btnHeart.setOnClickListener {
            val lottie = findViewById<LottieAnimationView>(R.id.lottie_heart)

            if (!heart) {
                //하트 움직이는 애니메이션 작동
                lottie.playAnimation()
                lottie.postDelayed({
                    lottie.pauseAnimation()
                }, 1000)
                likeCount++
                heart = true
            } else {
                //하트 빈값으로 만들어줌
                lottie.frame = 0
                if (likeCount > 0) {
                    likeCount--
                }
                heart = false
            }

            Log.d("test123", likeCount.toString())


        }

        //댓글 관련 구현한 로직 추가적으로 공부해봐야 함!!
        commentList.forEach {
            val view = LayoutInflater.from(this).inflate(R.layout.item_community_comment, null, false)
            val textView = view.findViewById<TextView>(R.id.tv_community_comment_content)
            textView.text = it.comment
            container.addView(view)
        }

//        val commentListView = findViewById<ListView>(R.id.listview_comment)
//        commentListView.adapter = commentAdapter

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