package com.example.listusergithub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val listUsers: List<ItemsItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textItem: TextView = view.findViewById(R.id.text_user)
        val btnDetail: TextView = view.findViewById(R.id.btn_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textItem.text = listUsers[position].login
        holder.btnDetail.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("LOGIN", listUsers[position].login)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listUsers.size
}