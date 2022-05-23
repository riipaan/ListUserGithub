package com.example.listusergithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listusergithub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vwBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vwBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vwBinding.root)

        val vwModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        vwModel.userItems.observe(this) { userItems ->
            setUserItems(userItems)
        }

        vwModel.isLoading.observe(this) {
            showProgressBar(it)
        }

        val layoutManager = LinearLayoutManager(this)
        vwBinding.listUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        vwBinding.listUsers.addItemDecoration(itemDecoration)

    }

    private fun setUserItems(userItems: List<ItemsItem>) {
//        val listUsers = ArrayList<String>()
//        for (item in userItems.take(20)) {
//            listUsers.add(
//                """
//                    ${item.login}
//                """.trimIndent()
//            )
//        }
//
//        val adapater = UserAdapter(listUsers)
        val temp = userItems.take(20)
        val adapater = UserAdapter(temp)
        vwBinding.listUsers.adapter = adapater
    }

    private fun showProgressBar(isLoading: Boolean) {
        vwBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}