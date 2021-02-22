package com.example.adressbook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adressbook.R
import com.example.adressbook.model.db.BookAddressEntity
import kotlinx.android.synthetic.main.note_list_activity.view.*


class BookListAdapter(
    private val bookAddressList: List<BookAddressEntity>,
    val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<BookListAdapter.BookAddressListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAddressListViewHolder {
        return BookAddressListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_list_activity, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookAddressListViewHolder, position: Int) {
        holder.addressName.text = bookAddressList[position].name
        holder.addressLastName.text = bookAddressList[position].lastName
        holder.phoneNumber.text = bookAddressList[position].phoneNumber.toString()
        (holder as BookAddressListViewHolder).bind(bookAddressList[position].id, clickListener)

    }

    override fun getItemCount(): Int {
        return bookAddressList.size
    }

    class BookAddressListViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        val addressName: TextView = itemView.addressName
        val addressLastName: TextView = itemView.addressLastName
        val phoneNumber: TextView = itemView.phoneNumber

        fun bind(id:Int, clickListener: (Int) -> Unit){
            itemView.setOnClickListener { clickListener(id) }
        }
    }
}