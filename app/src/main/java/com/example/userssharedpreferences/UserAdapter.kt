package com.example.userssharedpreferences

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.userssharedpreferences.databinding.UserRowAltBinding

class UserAdapter(private val users: List<User>, private val listener: OnClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.user_row_alt, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)

        with(holder) {
            setListener(user, position + 1)
            Glide.with(context)
                .load(user.urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.ivImage)
            binding.tvOrder.text = (position + 1).toString() /*user.id.toString()*/
            binding.tvName.text = user.getFullName()
        }
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = UserRowAltBinding.bind(view)

        fun setListener(user: User, position: Int) {
            binding.root.setOnClickListener {
                listener.onClick(user, position)
            }
        }
    }

    interface OnClickListener {
        fun onClick(user: User, position: Int)
    }
}