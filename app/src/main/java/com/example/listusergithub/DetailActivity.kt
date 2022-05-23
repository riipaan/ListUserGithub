package com.example.listusergithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.listusergithub.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var vwBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vwBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(vwBinding.root)

        val vwModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        val login = intent.getStringExtra("LOGIN")
        vwModel.findDetail(login.toString())

        vwModel.userDetail.observe(this) { userDetail ->
            setUserDetail(userDetail)
        }

        vwModel.isLoading.observe(this) {
            showProgressBar(it)
        }

    }

    private fun setUserDetail(userDetails: DetailResponse) {
        vwBinding.textName.text = this.resources.getString(R.string.name, userDetails.name)
        vwBinding.textFollowers.text = this.resources.getString(R.string.followers, userDetails.followers)
        vwBinding.textFollowing.text = this.resources.getString(R.string.following, userDetails.following)
        Glide.with(this)
            .load(userDetails.avatarUrl)
            .into(vwBinding.avatar)
    }

    private fun showProgressBar(isLoading: Boolean) {
        vwBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}