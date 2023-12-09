package com.example.taskmanager_4mon.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager_4mon.databinding.FragmentPhoneBinding
import com.example.taskmanager_4mon.databinding.ItemTaskBinding
import com.example.taskmanager_4mon.model.Book

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val list = arrayListOf<Book>()

    fun addBooks(newList: List<Book>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class BookViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book){
            binding.tvTittle.text = book.name
            binding.tvDescription.text = book.author
        }
    }
}