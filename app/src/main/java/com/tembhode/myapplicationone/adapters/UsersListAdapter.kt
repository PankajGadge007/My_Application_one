package com.tembhode.myapplicationone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tembhode.myapplicationone.R
import com.tembhode.myapplicationone.models.User

/**
 * Created by Pankaj Gadge on 10/25/2021.
 */
class UsersListAdapter(var list: ArrayList<User>, var mListener: UserListListener) :
    RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {

    interface UserListListener {
        fun onCardClick(u: User)
        fun onDeleteClick(u: User, position: Int)
        fun onEditClick(u: User)
    }

    class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var name: TextView = v.findViewById(R.id.txtView_name)
        var mobile: TextView = v.findViewById(R.id.txtView_mobile)
        var book: TextView = v.findViewById(R.id.txtView_book)
        var card: CardView = v.findViewById(R.id.clContainer)
        var icDelete: ImageView = v.findViewById(R.id.ivDelete_User)
        var icEdit: ImageView = v.findViewById(R.id.ivEdit_User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.name.text = user.name
        holder.mobile.text = user.mobile
        holder.book.text = user.book
        holder.card.setOnClickListener {
            mListener.onCardClick(user)
        }
        holder.icDelete.setOnClickListener {
            mListener.onDeleteClick(user, position)
            list.removeAt(position)
            notifyItemRemoved(position)
        }
        holder.icEdit.setOnClickListener {
            mListener.onEditClick(user)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}